package education.section0

object Answer36:

  def main(args: Array[String]): Unit =
    println(seqEx6(Seq("apple", "kiwi", "pear", "fig")))

  def seqEx6(words: Seq[String]): Map[Int, Seq[String]] =
    words.groupBy(word => word.length)
