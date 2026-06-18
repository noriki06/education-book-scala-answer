package education.section7

object Answer5:

  def main(args: Array[String]): Unit =
    println(forQuestion5(xs = Seq(1, 2), ys = Seq(1, 2)))
  /**
   *問題 5: 多重ジェネレータ ＋ ガード ＋ タプル
   */
  def forQuestion5(xs: Seq[Int], ys: Seq[Int]): Seq[(Int, Int)] =
    for {
      a <- xs
      b <- ys
      if (a + b) % 2 == 0
    } yield (a, b)
