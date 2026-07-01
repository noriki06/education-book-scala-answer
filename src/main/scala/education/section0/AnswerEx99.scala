package education.section0

object AnswerEx99:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  import scala.util.{Success, Failure}

  def main(args: Array[String]): Unit =
    futureQuestion2("123").onComplete {
      case Success(n) => println(n)
      case Failure(e) => println(s"失敗: $e")
    }

    futureQuestion2("abc").onComplete {
      case Success(n) => println(n)
      case Failure(e) => println(s"失敗: $e")
    }

    Thread.sleep(1000)

  def futureQuestion2(s: String): Future[Int] =
    Future {
      s.toInt
    }
