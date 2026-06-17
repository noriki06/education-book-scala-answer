package education.section7

object AnswerEx1:

  def main(args: Array[String]): Unit =
    println(forEx1(Seq(2, 3)))

  def forEx1(xs: Seq[Int]): Seq[String] =
    for {
      a <- xs
      b <- xs
    } yield s"$a x $b = ${a * b}"
