package education.section5

object AnswerEx4:

  def msEx4(prices: Map[String, Int], order: Seq[String]): Int =
    order.toSeq(prices._1 => prices.get(prices._1).getOrElse(0))

  def main(args: Array[String]): Unit =
    println(msEx4(Map("apple" -> 120, "banana" -> 200), Seq("apple", "banana", "apple", "melon")))
