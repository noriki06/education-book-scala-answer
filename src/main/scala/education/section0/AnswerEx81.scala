package education.section0

object AnswerEx81:

  def main(args: Array[String]): Unit =
    println(forQuestion6(Some(3), Some(4)): Option[Int])

  def forQuestion6(a: Option[Int], b: Option[Int]): Option[Int] =
    for {
      x <- a
      y <- b
    } yield x + y
