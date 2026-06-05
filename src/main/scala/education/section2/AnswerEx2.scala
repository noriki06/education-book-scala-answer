package education.section2

object AnswerEx2:

  def divide(a: Int, b: Int): Option[Int] =
    if b == 0 then None
    else Some(a / b)
    
  def main(args: Array[String]): Unit =
    println(divide(10, 2))
    println(divide(10, 0))
