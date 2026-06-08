package education.section3

object AnswerEx6:
  def seqEx6(words: Seq[String]): Map[Int, Seq[String]] =
    words.groupBy(w => w.length)

  def main(args: Array[String]): Unit =
    println(seqEx6(Seq("apple", "kiwi", "pear", "fig")))
