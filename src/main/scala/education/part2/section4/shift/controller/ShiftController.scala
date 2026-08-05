package education.part2.section4.shift.controller

import javax.inject.{ Inject, Singleton }
import java.time.{ Duration, LocalDateTime }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.DurationInt
import education.part2.section4.shift.DIContainer
import education.part2.section4.shift.model.{ Work, ShiftSummary }
import education.part2.section4.shift.function.WorkBreakdownCalculator
import education.part2.section4.shift.persistence.EduRepositoryFacade
import education.part2.section4.shift.tool.ResetTables

/**
 * 入口（Play を使わない学習ジョブ）。
 * テーブルを初期化し、勤務実績を保存して、内訳と深夜時間の合計を提示するまでを 1 本の流れで実行する。
 */
object Main:
  def main(args: Array[String]): Unit =
    import scala.concurrent.ExecutionContext.Implicits.global

    // ① テーブルを初期化する
    ResetTables.main(Array.empty)

    val controller = DIContainer.getInstance(classOf[ShiftController])
    val summaries = Await.result(
      for
        ids       <- controller.seed()          // ② 実例（確定分）と未記録（予定分）を混ぜて保存する
        summaries <- controller.summaries(ids)   // ③ 全件を取得し、勤務内訳を算出する
      yield summaries,
      60.seconds
    )

    summaries.foreach: s =>
      println(
        f"${s.name}: 予定 ${s.planStart}〜${s.planEnd} 状態=${s.status} " +
        f"総${hours(s.breakdown.totalTime)}%.2fh 超過${hours(s.breakdown.overtime)}%.2fh 深夜${hours(s.breakdown.lateNight)}%.2fh"
      )

    // ④ 全シッターの深夜時間の合計
    println(f"[全シッター] 深夜時間の合計 = ${hours(controller.totalLateNight(summaries))}%.2fh")

  private def hours(d: Duration): Double = d.toMinutes / 60.0


/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class ShiftController @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):

  /**
   * 勤務実績を保存する。実例の確定分（実勤務あり）と、実勤務が未記録の予定分を混ぜる。
   */
  def seed(): Future[Seq[Work.Id]] =
    val works = Seq(
      // 例1：予定 7/1 21:00〜7/2 00:00、実勤務 7/1 21:00〜7/2 01:00（総4h/超過1h/深夜3h）
      Work(
        None, "田中花子",
        LocalDateTime.of(2026, 7, 1, 21, 0),  LocalDateTime.of(2026, 7, 2, 0, 0),
        Some(LocalDateTime.of(2026, 7, 1, 21, 0)), Some(LocalDateTime.of(2026, 7, 2, 1, 0)),
        Work.Status.Finish
      ).toWithNoId,
      // 例2：予定・実勤務ともに 7/1 20:30〜7/2 00:30（総4h/超過0h/深夜2.5h）
      Work(
        None, "佐藤みどり",
        LocalDateTime.of(2026, 7, 1, 20, 30), LocalDateTime.of(2026, 7, 2, 0, 30),
        Some(LocalDateTime.of(2026, 7, 1, 20, 30)), Some(LocalDateTime.of(2026, 7, 2, 0, 30)),
        Work.Status.Finish
      ).toWithNoId,
      // 実勤務が未記録の予定分（まだ打刻前）
      Work(
        None, "鈴木一郎",
        LocalDateTime.of(2026, 7, 3, 9, 0), LocalDateTime.of(2026, 7, 3, 12, 0),
        None, None,
        Work.Status.Plan
      ).toWithNoId
    )
    Future.sequence(works.map(edu.work.add))

  /**
   * 保存した勤務実績を取得し、その時刻から勤務内訳を算出して提示する（問6）。
   * 実勤務が未記録なら、仕様どおり予定を実勤務として算出する。
   */
  def summaries(ids: Seq[Work.Id]): Future[Seq[ShiftSummary]] =
    edu.work.filter(ids).map(_.map(toSummary))

  private def toSummary(work: Work.EmbeddedId): ShiftSummary =
    val w      = work.v
    val plan   = (w.planStart, w.planEnd)
    val actual = for
      start <- w.actualStart
      end   <- w.actualEnd
    yield (start, end)
    ShiftSummary(w.name, w.planStart, w.planEnd, w.status, WorkBreakdownCalculator(plan, actual))

  /** 全シッターの深夜時間の合計（給与計算の下ごしらえ） */
  def totalLateNight(summaries: Seq[ShiftSummary]): Duration =
    summaries.foldLeft(Duration.ZERO)((acc, s) => acc.plus(s.breakdown.lateNight))
