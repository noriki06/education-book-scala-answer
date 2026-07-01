package education.section0

object AnswerEx105:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =
    val re1 = slow("1", 10)
    val re2 = slow("2", 20)


    val result: Future[Int] =
      for {
        a <- re1
        b <- re2
      } yield a + b

    println(Await.result(result, Duration.Inf))



  def slow(label: String, value: Int): Future[Int] =
    Future {
      println(s"[$label] 開始")
      Thread.sleep(1000)
      println(s"[$label] 終了")
      value
    }
