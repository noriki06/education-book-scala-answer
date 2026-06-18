package education.section8

object Answer5:

  import scala.util.{Try, Success, Failure}

  def tryQuestion5(s: String): Try[Int] =
    Try(s.toInt)


  def main(args: Array[String]): Unit =
    println(tryQuestion5("123") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
    )
    println(tryQuestion5("abc") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
    )
