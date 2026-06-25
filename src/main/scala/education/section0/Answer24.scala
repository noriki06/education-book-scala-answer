package education.section0

object Answer24:

  def main(args: Array[String]): Unit =
    println(seqQuestion2(Seq(1, 3, 4, 6, 9)))

  def seqQuestion2(numSeq: Seq[Int]): Seq[Int] =
    numSeq.filter(n => n % 3 == 0)
