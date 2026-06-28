package education.section0

object Answer72:

  def main(args: Array[String]): Unit =
    println(msEx6(Map("apple" -> 300, "banana" -> 1200, "kiwi" -> 800)))

  def msEx6(sales: Map[String, Int]): Seq[(String, Int)] =
    sales.toSeq.sortBy(sale => -sale._2)
