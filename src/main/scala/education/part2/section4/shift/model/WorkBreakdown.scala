package education.part2.section4.shift.model

import ixias.core.model.*

/**
 * 勤務内訳
 */
case class WorkBreakdown(
  actualStart:  LocalDateTime, // 実勤務の開始
  actualEnd:    LocalDateTime, // 実勤務の終了
  totalTime:      Duration,      // 総時間
  overtime:     Duration,      // 超過時間
  lateNight:    Duration,      // 深夜時間
)

object WorkBreakdown:
  val NormalStart: LocalTime = LocalTime.of(5, 0)
  val NormalEnd: LocalTime = LocalTime.of(22, 0)
