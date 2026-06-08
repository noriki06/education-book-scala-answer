package education.section3

object Answer5:
  def seqQuestion5(numSeq: Seq[Int]): Boolean =
    numSeq.exists(num => num >= 100)

  def main(args: Array[String]): Unit =
    println(seqQuestion5(Seq(1, 50, 120)))
    println(seqQuestion5(Seq(1, 2, 3)))
