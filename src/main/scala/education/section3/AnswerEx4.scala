package education.section3

object AnswerEx4:
  def seqEx4(numSeq: Seq[Int]): Int =
    numSeq.find(_ >= 100).map(_ * 2).getOrElse(0)
    //100以上の最初の要素は取得/それを２倍/なければ0,あれば数値
  def main(args: Array[String]): Unit =
    println(seqEx4(Seq(1, 50, 120, 200)))
    println(seqEx4(Seq(1, 2, 3)))
