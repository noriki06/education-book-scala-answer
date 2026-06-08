package education.section3

object AnswerEx8:
  def seqEx8(numSeq: Seq[Int]): Seq[Int] =
    numSeq.foldLeft(Seq.empty[Int])((acc, n) => n +: acc)
    //空の配列を作って、要素を 先頭に追加（+:）
  def main(args: Array[String]): Unit =
    println(seqEx8(Seq(1, 2, 3, 4)))
