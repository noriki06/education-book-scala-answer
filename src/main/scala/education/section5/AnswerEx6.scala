package education.section5

object AnswerEx6:

  def msEx6(sales: Map[String, Int]): Seq[(String, Int)] =
    sales.toSeq.sortBy(s => -s._2)


  def main(args: Array[String]): Unit =
    println(msEx6(Map("apple" -> 300, "banana" -> 1200, "kiwi" -> 800)))
