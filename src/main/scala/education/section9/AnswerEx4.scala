package education.section9

object AnswerEx4:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def findUserId(name: String): Future[Int] =
    Future {

    }

  def findProfile(userId: Int): Future[String] =
    Future {

    }

  def findNotice(): Future[String] =
    Future {

    }


  def loadPage(name: String): Future[(String, String)] =
    Future.sequence(ids.map(fetch))


  def main(args: Array[String]): Unit =
    val start = System.currentTimeMillis()
    val noticeresult =
    val result: Seq[String] = Await.result(fetchAll(Seq(1, 2, 3)), Duration.Inf)
    val end = System.currentTimeMillis()
    println(result)
    println(s"結果: $result, かかった時間: ${end - start} ms")
