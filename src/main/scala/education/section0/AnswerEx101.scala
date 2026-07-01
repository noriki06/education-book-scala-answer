package education.section0

object AnswerEx101:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    println(Await.ready(futureQuestion4(3), Duration.Inf))

  def step1(n: Int): Future[Int] =
    Future {
      n + 1
    }

  def step2(n: Int): Future[Int] =
    Future {
      n * 2
    }

  def futureQuestion4(n: Int): Future[Int] =
    step1(n).flatMap(n => step2(n))
