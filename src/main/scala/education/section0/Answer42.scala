package education.section0

object Answer42:

  def main(args: Array[String]): Unit =
    println(tupleQuestion2(("Alice", 80)))

  def tupleQuestion2(pair: (String, Int)): String =
    s"${pair._1}さんは${pair._2}点"
