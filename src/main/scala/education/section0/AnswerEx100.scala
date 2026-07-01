package education.section0

object AnswerEx100:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    println(Await.ready(futureQuestion3(5).map(_ * 10), Duration.Inf))

  def futureQuestion3(n: Int): Future[Int] =
    Future {
      n
    }
