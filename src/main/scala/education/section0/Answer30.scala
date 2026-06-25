package education.section0

object Answer30:

  def main(args: Array[String]): Unit =
    println(seqQuestion8(Seq(1, 2, 3)))

  def seqQuestion8(numSeq: Seq[Int]): Seq[Int] =
    numSeq.flatMap(num => Seq(num, num * 2))
