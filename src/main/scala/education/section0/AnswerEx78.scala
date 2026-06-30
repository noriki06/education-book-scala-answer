package education.section0

object AnswerEx78:

  def main(args: Array[String]): Unit =
    println(forQuestion3(Seq(1, 2, 3, 4)))

  def forQuestion3(numSeq: Seq[Int]): Seq[Int] =
    for {
      a <- numSeq
      if a % 2 == 0
    } yield a * a
