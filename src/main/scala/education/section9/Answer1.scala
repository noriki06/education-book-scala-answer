package education.section9

object Answer1:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def futureQuestion1(n: Int): Future[Int] =
    Future {
      n * 2
    }

  def main(args: Array[String]): Unit =
    val resultFuture: Future[Int] = futureQuestion1(21)
    val result: Int = Await.result(resultFuture, Duration.Inf)
    println(futureQuestion1(result))
