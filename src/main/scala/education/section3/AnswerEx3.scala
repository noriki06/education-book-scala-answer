package education.section3

object AnswerEx3:
  def seqEx3(numSeqSeq: Seq[Seq[Int]]): Seq[Seq[Int]] =
    numSeqSeq.filter(inner => inner.exists(_ % 3 == 0))
              //3の倍数が存在するか、それを抽出
  def main(args: Array[String]): Unit =
    println(seqEx3(Seq(Seq(1, 2), Seq(3, 9), Seq(4, 5))))
