package education.section0

object AnswerEx91:

  import scala.util.{Try, Success, Failure}


  def main(args: Array[String]): Unit =
    println(tryQuestion5("123") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
    )

  def tryQuestion5(s: String): Try[Int] =
    Try(s.toInt)
