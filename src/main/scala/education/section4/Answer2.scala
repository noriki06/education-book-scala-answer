package education.section4

object Answer2:
  def tupleQuestion2(pair: (String, Int)): String =
    s"${pair._1}さんは${pair._2}点"


  def main(args: Array[String]): Unit =
    println(tupleQuestion2(("Alice", 80)))


