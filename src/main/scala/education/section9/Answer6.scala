package education.section9

object Answer6:

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration.*

  def futureQuestion6(s: String): Future[Int] =
    Future { s.toInt }.recover {
      case _: NumberFormatException => -1
    }

  def main(args: Array[String]): Unit =
    val result1: Int = Await.result(futureQuestion6("100"), Duration.Inf)   // 完了するまで待って、結果（Int）を受け取る
    println(result1)

    val result2: Int = Await.result(futureQuestion6("abc"), Duration.Inf)   // 完了するまで待って、結果（Int）を受け取る
    println(result2)
