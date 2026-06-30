package education.section0

object AnswerEx79:

  def main(args: Array[String]): Unit =
    println(forQuestion4(Seq(1, 2), Seq(10, 100)))

  def forQuestion4(xs: Seq[Int], ys: Seq[Int]): Seq[Int] =
    for {
      a <- xs
      b <- ys
    } yield a * b
