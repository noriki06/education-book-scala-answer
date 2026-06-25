package education.section0

object Answer38:

  def main(args: Array[String]): Unit =
    println(seqEx8(Seq(1, 2, 3, 4)))

  def seqEx8(numSeq: Seq[Int]): Seq[Int] =
    numSeq.foldLeft(Seq.empty[Int])((acc, n) => n +: acc)
