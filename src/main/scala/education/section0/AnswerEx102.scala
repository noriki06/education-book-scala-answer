package education.section0

object AnswerEx102:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    println(Await.ready(futureQuestion5(3), Duration.Inf))

  def step1(n: Int): Future[Int] =
    Future {
      n + 1
    }

  def step2(n: Int): Future[Int] =
    Future {
      n * 2
    }

  def futureQuestion5(n: Int): Future[Int] =
    for {
      a <- step1(n)
      b <- step2(a)
    } yield b
