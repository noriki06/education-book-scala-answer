package education.section3

object Answer7:
  def seqQuestion7(numSeq: Seq[Int]): Int =
    numSeq.foldLeft(0)((acc, n) => acc + n)

  def main(args: Array[String]): Unit =
    println(seqQuestion7(Seq(1, 2, 3, 4)))
    println(seqQuestion7(Seq()))
