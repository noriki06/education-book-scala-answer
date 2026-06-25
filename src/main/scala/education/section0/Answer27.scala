package education.section0

object Answer27:

  def main(args: Array[String]): Unit =
    println(seqQuestion5(Seq(1, 50, 120)))

  def seqQuestion5(numSeq: Seq[Int]): Boolean =
    numSeq.exists(num => num >= 100)
