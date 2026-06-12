package education.section5

object AnswerEx3:

  def msEx3(pairs: Seq[(String, Int)]): Map[String, Int] =
    pairs.toMap

  def main(args: Array[String]): Unit =
    println(msEx3(Seq(("apple", 120), ("banana", 200))))
