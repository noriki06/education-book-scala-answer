package education.section7

object AnswerEx1:

  def main(args: Array[String]): Unit =
    println(forEx1(Seq(2, 3)))
  /**
   *問題 1: 多重ジェネレータで組み合わせを作る — 九九の一部
   */
  def forEx1(xs: Seq[Int]): Seq[String] =
    for {
      a <- xs
      b <- xs
    } yield s"$a x $b = ${a * b}"
