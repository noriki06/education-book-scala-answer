package education.section0

object Answer29:

  def main(args: Array[String]): Unit =
    println(seqQuestion7(Seq(1, 2, 3, 4)))

  def seqQuestion7(numSeq: Seq[Int]): Int =
    numSeq.foldLeft(0)((a, n) => a + n)
