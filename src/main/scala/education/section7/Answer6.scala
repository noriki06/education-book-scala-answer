package education.section7

object Answer6:

  def main(args: Array[String]): Unit =
    println(forQuestion6(a = Some(3), b = Some(4)))
    println(forQuestion6(a = Some(3), b = None ))
  /**
   *問題 6: Option の for 式
   */
  def forQuestion6(a: Option[Int], b: Option[Int]): Option[Int] =
    for {
      number1 <- a
      number2 <- b
    } yield number1 + number2
