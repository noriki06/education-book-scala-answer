package education.part2.section4.shift.function

import java.time.{Duration, LocalDateTime}
import education.part2.section4.shift.model.WorkBreakdown

/**
 * 勤務内訳（総時間・超過時間・深夜時間）を算出する。
 * 実勤務（actual）が未記録なら、予定（plan）をそのまま実勤務とみなす。
 */
object WorkBreakdownCalculator extends Function2[(LocalDateTime, LocalDateTime), Option[(LocalDateTime, LocalDateTime)], WorkBreakdown]:

  def apply(plan: (LocalDateTime, LocalDateTime), actual: Option[(LocalDateTime, LocalDateTime)]): WorkBreakdown =
    val (planStart, planEnd) = plan
    val (rawStart, rawEnd)   = actual.getOrElse(plan)
    val (start, end)         = RoundWorkTime(rawStart, rawEnd)

    val totalTime     = Duration.between(start, end)
    val planDuration  = Duration.between(planStart, planEnd)
    val overtime      = if totalTime.compareTo(planDuration) > 0 then totalTime.minus(planDuration) else Duration.ZERO
    val normalTime    = CoreTime(start, end)
    val lateNight     = totalTime.minus(normalTime)

    WorkBreakdown(start, end, totalTime, overtime, lateNight)
