package education.section0

object AnswerEx76:

  def main(args: Array[String]): Unit =
    println(forQuestion1(Seq(1, 2, 3)))

  def forQuestion1(numSeq: Seq[Int]): Seq[Int] =
    for {
      a <- numSeq
    } yield a * 3
