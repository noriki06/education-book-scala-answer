package education.section3

object Answer8:
  def seqQuestion8(numSeq: Seq[Int]): Seq[Int] =
    numSeq.flatMap(a => Seq(a, a * 2))


  def main(args: Array[String]): Unit =
    println(seqQuestion8(Seq(1, 2, 3)))
