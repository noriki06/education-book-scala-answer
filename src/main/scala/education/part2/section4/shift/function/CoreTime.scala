package education.part2.section4.shift.function

import java.time.{Duration, LocalDate}
import education.part2.section4.shift.model.WorkBreakdown

object CoreTime
  extends Function2[LocalDateTime, LocalDateTime, Duration]:

  def apply(
      start: LocalDateTime,
      end: LocalDateTime
  ): Duration =

    val firstDay = start.toLocalDate
    val lastDay  = end.toLocalDate

    // 開始日～終了日までの日付を作る
    val days = Duration.between(firstDay, lastDay)

    // 1日ずつ通常帯を計算して合計する
    days.foldLeft(Duration.ZERO) { (total, day) =>

      val normalStart = day.atTime(WorkBreakdown.NormalStart)
      val normalEnd   = day.atTime(WorkBreakdown.NormalEnd)

      val overlapStart =
        if start.isAfter(normalStart) then start
        else normalStart

      val overlapEnd =
        if end.isBefore(normalEnd) then end
        else normalEnd

      val added =
        if overlapStart.isBefore(overlapEnd) then
          Duration.between(overlapStart, overlapEnd)
        else
          Duration.ZERO

      total.plus(added)
    }
