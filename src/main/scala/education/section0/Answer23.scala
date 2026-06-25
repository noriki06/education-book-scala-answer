package education.section0

object Answer23:

  def main(args: Array[String]): Unit =
    println(seqQuestion1(Seq(1, 2, 3)))

  def seqQuestion1(numSeq: Seq[Int]): Seq[Int] =
    numSeq.map(num => num * num)
