package education.section0

object AnswerEx80:

  def main(args: Array[String]): Unit =
    println(forQuestion5(Seq(1, 2), Seq(1, 2)))

  def forQuestion5(xs: Seq[Int], ys: Seq[Int]): Seq[(Int, Int)] =
    for {
      a <- xs
      b <- ys
      if (a + b) % 2 == 0
    } yield (a, b)
