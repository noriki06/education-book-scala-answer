package education.section3

object AnswerEx2:
  def seqEx2(numSeq: Seq[Int]): Double =
    if numSeq.isEmpty == true then 0.0
    else numSeq.sum.toDouble / numSeq.size



  def main(args: Array[String]): Unit =
    println(seqEx2(Seq(2, 4, 9)))
    println(seqEx2(Seq()))
