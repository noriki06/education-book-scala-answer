package education.section0

object AnswerEx87:

  def main(args: Array[String]): Unit =
    println(eitherQuestion1(5))

  def eitherQuestion1(n: Int): Either[String, Int] =
    n match
      case n if n >= 0 => Right(n)
      case _           => Left("負の数です")
