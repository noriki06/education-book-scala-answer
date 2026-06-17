package education.section7

object Answer2:

  def main(args: Array[String]): Unit =
    println(forQuestion2(Seq(-2, 3, 0, 5)))

  def forQuestion2(numSeq: Seq[Int]): Seq[Int] =
    for {
      n <- numSeq
      if n > 0
    } yield n
