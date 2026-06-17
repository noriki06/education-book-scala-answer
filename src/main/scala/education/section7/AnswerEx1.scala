package education.section7

object AnswerEx1:

  def main(args: Array[String]): Unit =
    println(forEx1(Seq(2, 3)))

  def forEx1(xs: Seq[Int]): Seq[String] =
    for {
      n <- xs
      x <- xs
      b <- (n, x)
    } yield s"$n x $x = $b"
