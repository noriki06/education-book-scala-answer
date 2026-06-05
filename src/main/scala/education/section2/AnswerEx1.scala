package education.section2

object AnswerEx1:

  def optionEx1(num: Option[Int]): Int =
    num.map {_ * 2}.getOrElse(-1)
    



  def main(args: Array[String]): Unit =
    println(optionEx1(Some(5)))
    println(optionEx1(None))
