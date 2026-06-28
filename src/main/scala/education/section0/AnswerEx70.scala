package education.section0

object Answer70:

  def main(args: Array[String]): Unit =
    println(msEx4(Map("apple" -> 120, "banana" -> 200), Seq("apple", "banana", "apple", "melon")))

  def msEx4(prices: Map[String, Int], order: Seq[String]): Int =
    order.map(name => prices.get(name).getOrElse(0)).sum
