package education.section8

object Answer6:

  import scala.util.{Try, Success, Failure}

  def tryQuestion5(s: String): Try[Int] =
    Try(s.toInt)

  def tryQuestion6(a: String, b: String): Try[Int] =
    for {
      x <- a.tryQuestion5
      y <- b.tryQuestion5
    } yield x * y


  def main(args: Array[String]): Unit =
    println(tryQuestion6(a = "3", b = "4") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
    )
    println(tryQuestion6(a = "3", b = "y:") match
      case Success(n) => s"Success($n)"
      case Failure(s) => s"Failure($s)"
    )
