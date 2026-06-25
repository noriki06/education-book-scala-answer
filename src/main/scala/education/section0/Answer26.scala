package education.section0

object Answer26:

  def main(args: Array[String]): Unit =
    println(seqQuestion3(Seq(1, 3, 4, 6)))

  def seqQuestion3(numSeq: Seq[Int]): Option[Int] =
    numSeq.find(num => num % 2 == 0)
