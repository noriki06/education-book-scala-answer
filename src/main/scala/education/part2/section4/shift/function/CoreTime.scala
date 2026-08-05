package education.part2.section4.shift.function

import java.time.{Duration, LocalDate, LocalDateTime}
import education.part2.section4.shift.model.WorkBreakdown

object CoreTime extends Function2[LocalDateTime, LocalDateTime, Duration]:

  def apply(start: LocalDateTime, end: LocalDateTime): Duration =
    // 1. 開始日から終了日までの LocalDate のリスト（Sequence）を作る
    val startDate = start.toLocalDate
    val endDate   = end.toLocalDate
    val days      = LazyList.iterate(startDate)(_.plusDays(1)).takeWhile(!_.isAfter(endDate))

    // 2. 1日ごとの通常帯時間を計算する関数
    def calculateDailyCoreTime(day: LocalDate): Duration =
      // その日の通常帯の開始（05:00）と終了（22:00）
      val normalStart = day.atTime(WorkBreakdown.NormalStart)
      val normalEnd   = day.atTime(WorkBreakdown.NormalEnd)

      // 実勤務と通常帯の「重なる範囲」を求める（遅い開始〜早い終了）
      val overlapStart = if start.isAfter(normalStart) then start else normalStart
      val overlapEnd   = if end.isBefore(normalEnd) then end else normalEnd

      // 開始より終了のほうが後なら時間を計算、そうでなければ 0
      if overlapStart.isBefore(overlapEnd) then
        Duration.between(overlapStart, overlapEnd)
      else
        Duration.ZERO

    // 3. 各日の時間を計算して、すべて足し算
    days.foldLeft(Duration.ZERO)((acc, day) => acc.plus(calculateDailyCoreTime(day)))
