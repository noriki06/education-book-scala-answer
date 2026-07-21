package education.part2.section4.shift.function

import java.time.{Duration, LocalDate, LocalDateTime}
import education.part2.section4.shift.model.WorkBreakdown

/**
 * 通常帯(05:00～22:00)の勤務時間を求める
 */
object CoreTime
  extends Function2[LocalDateTime, LocalDateTime, Duration]:

  def apply(
      start: LocalDateTime,
      end: LocalDateTime
  ): Duration =
    val firstDay = start.toLocalDate

    val lastDay = end.toLocalDate

    def loop(day: LocalDate, total: Duration): Duration =
      if firstDay == lastDay then total
      else
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

        loop(
          day.plusDays(1),
          total.plus(added)
        )

    loop(
      start.toLocalDate,
      Duration.ZERO
    )
