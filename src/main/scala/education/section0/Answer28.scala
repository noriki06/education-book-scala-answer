package education.section0

object Answer28:

  def main(args: Array[String]): Unit =
    println(seqQuestion6(Seq(10, 20, 30)))

  def seqQuestion6(numSeq: Seq[Int]): Int =
    numSeq.headOption.getOrElse(-1)
