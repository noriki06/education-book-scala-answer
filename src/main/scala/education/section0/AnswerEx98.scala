package education.section0

object AnswerEx98:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    println(Await.ready(futureQuestion1(21), Duration.Inf))

  def futureQuestion1(n: Int): Future[Int] =
    Future {
      n * 2
    }
