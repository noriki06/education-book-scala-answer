package education.section0

object AnswerEx77:

  def main(args: Array[String]): Unit =
    println(forQuestion2(Seq(-2, 3, 0, 5)))

  def forQuestion2(numSeq: Seq[Int]): Seq[Int] =
    for {
      a <- numSeq
      if a > 0
    } yield a
