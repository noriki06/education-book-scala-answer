package education.section0

object AnswerEx82:

  def main(args: Array[String]): Unit =
    println(forEx1(Seq(2, 3)))

  def forEx1(xs: Seq[Int]): Seq[String] =
    for {
      a <- xs
      b <- xs
    } yield s"$a * $b = ${a * b}"
