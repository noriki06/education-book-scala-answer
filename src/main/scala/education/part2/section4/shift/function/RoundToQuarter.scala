package education.part2.section4.shift.function

import ixias.core.model.*
/**
 * 勤務時間を15分単位に丸める
 * 開始：切り捨て
 * 終了：切り上げ
 */
object RoundWorkTime extends Function2[LocalDateTime, LocalDateTime,(LocalDateTime, LocalDateTime) ]:

  def apply(start: LocalDateTime, end: LocalDateTime): (LocalDateTime, LocalDateTime) =

    val roundedStart =
      start.withMinute((start.getMinute / 15) * 15)
        .withSecond(0)
        .withNano(0)

    val roundedEnd =
      if end.getMinute % 15 == 0 && end.getSecond == 0 && end.getNano == 0 then
        end
      else
        end
          .plusMinutes(15 - end.getMinute % 15)
          .withSecond(0)
          .withNano(0)

    (roundedStart, roundedEnd)
