package education.section7

object AnswerEx2:

  def main(args: Array[String]): Unit =
    println(forEx2(20))
  /**
   *問題 2: 多重ジェネレータ ＋ ガード — ピタゴラス数
   */
  def forEx2(max: Int): Seq[(Int, Int, Int)] =
    for {
      a <- 1 to max
      b <- 1 to max
      c <- 1 to max
      if a <= b && b <= c && a*a + b*b == c*c
    } yield (a, b, c)
