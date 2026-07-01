package education.section0

object AnswerEx106:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def main(args: Array[String]): Unit =

    println(Await.result(fetchAll(Seq(1, 2, 3)), Duration.Inf))



  def fetch(id: Int): Future[String] =
    Future {
      Thread.sleep(1000)
      s"item$id"
    }


  def fetchAll(ids: Seq[Int]): Future[Seq[String]] =
    Future.sequence(ids.map(fetch))
