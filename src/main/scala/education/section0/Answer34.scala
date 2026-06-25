package education.section0

object Answer34:

  def main(args: Array[String]): Unit =
    println(seqEx4(Seq(1, 50, 120, 200)))

  def seqEx4(numSeq: Seq[Int]): Int =
    numSeq.find(_ >= 100).map(_ * 2).getOrElse(0)
