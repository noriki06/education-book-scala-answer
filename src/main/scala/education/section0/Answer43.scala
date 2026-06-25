package education.section0

object Answer43:

  def main(args: Array[String]): Unit =
    println(tupleQuestion3(("Alice", 80)))

  def tupleQuestion3(pair: (String, Int)): (Int, String) =
    val (name, score) = pair
    (score, name)
