package education.section0

object AnswerEx92:
  import scala.util.{Try, Success, Failure}

  def tryQuestion5(s: String): Try[Int] =
    Try(s.toInt)

  def main(args: Array[String]): Unit =
    println(tryQuestion6("3", "y") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
      )

  def tryQuestion6(a: String, b: String): Try[Int] =
    for {
      x <- tryQuestion5(a)
      y <- tryQuestion5(b)
    } yield x * y
