package education.section9

object AnswerEx4:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def findUserId(name: String): Future[Int] =
    Future {
      Thread.sleep(1000)
      name.toInt
    }

  def findProfile(userId: Int): Future[String] =
    Future {
      Thread.sleep(1000)
      userId.toString
    }

  def findNotice(): Future[String] =
    Future {
      Thread.sleep(1000)


    }


  def loadPage(name: String): Future[(String, String)] =
    val noticeFuture: Future[String] = findNotice()
    val profileFuture: Future[String] =
      for {
        userId <- findUserId(name)
        profile <- findProfile(userId)
      } yield profile

    for {
      profile <- profileFuture
      notice <- noticeFuture
    } yield (profile, notice)


  def main(args: Array[String]): Unit =
    val start = System.currentTimeMillis()
    val result: Seq[String] = Await.result(loadPage("scala"), Duration.Inf)
    val end = System.currentTimeMillis()
    println(result)
    println(s"結果: $result, かかった時間: ${end - start} ms")
