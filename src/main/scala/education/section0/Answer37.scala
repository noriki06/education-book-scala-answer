package education.section0

object Answer37:

  def main(args: Array[String]): Unit =
    println(seqEx7(Seq("12", "x", "30")))

  def seqEx7(strSeq: Seq[String]): Seq[Int] =
    strSeq.flatMap(str => str.toIntOption)
