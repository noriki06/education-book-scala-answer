package education.part2.section4.library.controller

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }
import education.part2.section4.library.model.Loan
import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer7:
  def main(args: Array[String]): Unit =
    val ans3C = DIContainer.getInstance(classOf[Answer3Controller])
    val ans4C = DIContainer.getInstance(classOf[Answer4Controller])
    val ans5C = DIContainer.getInstance(classOf[Answer5Controller])
    val ans6C = DIContainer.getInstance(classOf[Answer6Controller])
    val ans7C = DIContainer.getInstance(classOf[Answer7Controller])
    Await.result(ans7C.totalMonth(ans5C, ans4C, ans3C), 60.seconds)
    println("[OK] demo 完了")


@Singleton
class Answer7Controller @Inject()
  (edu: EduRepositoryFacade, ans5C: Answer5Controller)(using ExecutionContext):
  /**
   * 月ごとの貸出件数メソッド
   */
  def totalMonth(ans5C: Answer5Controller, ans4C: Answer4Controller, ans3C: Answer3Controller): Future[Seq[(Int, Int)]] =
    for
      seqId <- ans5C.invoke(ans3C.invoke(), ans4C)
      onlysec = seqId.collect { case Right(v) => v }
      loans <- edu.loan.filter(onlysec)
    yield
      loans
        .filter(_.v.status == Loan.Status.Rent)
        .groupBy(_.v.loanedAt.getMonthValue)
        .view
        .mapValues(_.size)
        .toSeq
        .sorted
