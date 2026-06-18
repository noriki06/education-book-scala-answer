package education.section7

object Answer1:

  def main(args: Array[String]): Unit =
    println(forQuestion1(Seq(1, 2, 3)))
  /**
   *問題 1: 単一ジェネレータ ＋ yield
   */
  def forQuestion1(numSeq: Seq[Int]): Seq[Int] =
    for {
      n <- numSeq
    } yield n * 3
