package education.section0

object Answer32:

  def main(args: Array[String]): Unit =
    println(seqEx2(Seq(2, 4, 9)))

  def seqEx2(numSeq: Seq[Int]): Double =
    if numSeq.isEmpty then 0.0
    else                   numSeq.sum.toDouble / numSeq.size
