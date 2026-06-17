package education.section7

object Answer3:

  def main(args: Array[String]): Unit =
    println(forQuestion3(Seq(1, 2, 3, 4)))

  def forQuestion3(numSeq: Seq[Int]): Seq[Int] =
    for {
      n <- numSeq
      if   n % 2 == 0
    } yield n * n
