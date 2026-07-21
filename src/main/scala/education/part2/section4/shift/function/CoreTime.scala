package education.part2.section4.shift.function

import java.time.{Duration, LocalDate, LocalDateTime}

/**
 * 通常帯(05:00～22:00)の勤務時間を求める
 */
object CoreTime
  extends Function2[LocalDateTime, LocalDateTime, Duration]:

  def apply(
      start: LocalDateTime,
      end: LocalDateTime
  ): Duration =

    val lastDay = end.toLocalDate

    def loop(
        day: LocalDate,
        total: Duration
    ): Duration =

      if day.isAfter(lastDay) then
        total

      else

        val normalStart = day.atTime(5, 0)
        val normalEnd   = day.atTime(22, 0)

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
