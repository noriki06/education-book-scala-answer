package education.part2.section4.shift.model

import ixias.core.model.*

/**
 * 勤務内訳
 */
case class BD(
  actualStart:  LocalDateTime, // 実勤務の開始
  actualEnd:    LocalDateTime, // 実勤務の終了
  allTime:      Duration,      // 総時間
  overTime:     Duration,      // 超過時間
  lateNight:    Duration,      // 深夜時間
)
