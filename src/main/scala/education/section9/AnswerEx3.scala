package education.section9

object AnswerEx3:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def fetch(id: Int): Future[String] =
    Future {
      Thread.sleep(1000)
      s"item$id"
    }

  def fetchAll(ids: Seq[Int]): Future[Seq[String]] =
    Future.sequence(ids.map(fetch))


  def main(args: Array[String]): Unit =
    val start = System.currentTimeMillis()
    val result: Seq[String] = Await.result(fetchAll(Seq(1, 2, 3)), Duration.Inf)
    System.currentTimeMillis() - start
    println(result)
