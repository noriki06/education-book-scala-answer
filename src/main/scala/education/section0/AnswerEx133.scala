package education.section0

object AnswerEx133:

  val byLength: Ordering[String] = Ordering.by(_.length)


  def orderingQuestion1(words: Seq[String]): Seq[String] =
    words.sorted(using byLength)


  def main(args: Array[String]): Unit =
    println(orderingQuestion1(Seq("ccc", "a", "bb")))
