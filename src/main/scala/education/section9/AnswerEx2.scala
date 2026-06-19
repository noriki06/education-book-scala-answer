package education.section9

object AnswerEx2:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def slow(label: String, value: Int): Future[Int] =
    Future {
      println(s"[$label] 開始")
      Thread.sleep(1000) // 1秒待つ処理をシミュレート
      println(s"[$label] 終了")
      value
    }

  def main(args: Array[String]): Unit =
    val f1 = slow("一つ目の処理", 10)
    val f2 = slow("二つ目の処理", 20)

    val combinedFuture: Future[Int] =
      for {
        result1 <- f1
        result2 <- f2
      } yield result1 + result2

    val finalResult: Int = Await.result(combinedFuture, Duration.Inf)
    println(s"合計結果: $finalResult")
