package education.section9

object Answer3:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def futureQuestion3(n: Int): Future[Int] =
    Future {
      n
    }


  def main(args: Array[String]): Unit =
    val result: Int = Await.result(futureQuestion3(5).map(_ * 10), Duration.Inf)   // 完了するまで待って、結果（Int）を受け取る
    println(result)
