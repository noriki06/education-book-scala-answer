package education.section0

object AnswerEx103:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    println(Await.ready(futureQuestion6("abc"), Duration.Inf))

  def futureQuestion6(s: String): Future[Int] =
    Future {
      s.toInt
    }.recover {
      case _: NumberFormatException => -1
    }
