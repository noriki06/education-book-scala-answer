package education.section0

object AnswerEx107:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*


  def main(args: Array[String]): Unit =
    println(Await.ready(loadPage("scala"), Duration.Inf))

  def findUserId(name: String): Future[Int] =
    Future {
      Thread.sleep(1000)
      if (name == "scala") {
        123 // 仮のユーザーID
      } else {
        456
      }
    }


  def findProfile(userId: Int): Future[String] =
    Future {
      Thread.sleep(1000)
      s"ユーザー$userId のプロフィール情報"
    }



  def findNotice(): Future[String] =
    Future {
      Thread.sleep(1000)
      "システムからのお知らせです！"
    }

  def loadPage(name: String): Future[(String, String)] =
    val notice: Future[String] = findNotice()
    val til: Future[String] =
      for {
        a <- findUserId(name)
        b <- findProfile(a)
      } yield b

    for {
      a <- notice
      b <- til
    } yield (b, a)
