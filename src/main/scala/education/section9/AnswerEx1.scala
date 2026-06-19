package education.section9

object AnswerEx1:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  def slow(label: String, value: Int): Future[Int] =
    for {
      a <- slow(...)
      b <- slow(...)
    } yield a + b


    Thread.sleep(1000)



    def main(args: Array[String]): Unit =
