package education.part2.section4.shift.model

import ixias.core.model.*

/**
 * 勤務実績を読み取り、内訳まで算出したあとの「読み取り専用のまとめ」。
 * 保存はしない。Work（保存の形）から、読み出し時にそのつど組み立てる。
 */
case class ShiftSummary(
  name:      String,        // シッター名
  planStart: LocalDateTime, // 予定の開始
  planEnd:   LocalDateTime, // 予定の終了
  status:    Work.Status,   // 勤務の状態
  breakdown: WorkBreakdown  // 算出した勤務内訳
)
