package education.part2.section4.shift.function

import java.time.{Duration, LocalDate, LocalDateTime}

/**
 * 通常帯(05:00～22:00)の勤務時間を求める
 */
object CalculateNormalTime
  extends Function2[LocalDateTime, LocalDateTime, Duration]:

  def apply(
    start: LocalDateTime,
    end: LocalDateTime
  ): Duration =

    // 合計時間
    var total = Duration.ZERO

    // 勤務開始日
    var day: LocalDate = start.toLocalDate

    // 勤務終了日
    val lastDay = end.toLocalDate

    // 開始日～終了日まで1日ずつ計算
    while !day.isAfter(lastDay) do

      // その日の通常帯
      val normalStart = day.atTime(5, 0)
      val normalEnd   = day.atTime(22, 0)

      // 実勤務との重なり開始
      val overlapStart =
        if start.isAfter(normalStart) then start
        else normalStart

      // 実勤務との重なり終了
      val overlapEnd =
        if end.isBefore(normalEnd) then end
        else normalEnd

      // 重なっていれば加算
      if overlapStart.isBefore(overlapEnd) then
        total = total.plus(
          Duration.between(overlapStart, overlapEnd)
        )

      // 次の日へ
      day = day.plusDays(1)

    total
