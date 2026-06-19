package education.section9

object Answer4:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

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


  def main(args: Array[String]): Unit =
    val result: Int = Await.result(futureQuestion4(3), Duration.Inf)   // 完了するまで待って、結果（Int）を受け取る
    println(result)
