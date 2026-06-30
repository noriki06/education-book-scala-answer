package education.section0

object AnswerEx88:

  def main(args: Array[String]): Unit =
    println(eitherQuestion2(Right(7)))

  def eitherQuestion2(e: Either[String, Int]): Int =
    e.getOrElse(0)
