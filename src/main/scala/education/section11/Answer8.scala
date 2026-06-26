package education.section11

object Answer8:

  def orderingQuestion1(words: Seq[String]): Seq[String] =
    val byLength: Ordering[String] = Ordering.by(_.length)
    words.sorted(using byLength)

  def main(args: Array[String]): Unit =
    println(orderingQuestion1(Seq("ccc", "a", "bb")))
