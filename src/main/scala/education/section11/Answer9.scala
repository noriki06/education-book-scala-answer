package education.section11

object Answer9:


  import scala.concurrent.{Future, Await}
  import scala.concurrent.duration.*
  import scala.concurrent.ExecutionContext.Implicits.global
  // コメントアウトするとこう出た
  //val result1: Int = Await.result(Future { 1 + 2 }, 1.second)
//[error]    |                                                    ^
//[error]    |Cannot find an implicit ExecutionContext. You might add
//[error]    |an (implicit ec: ExecutionContext) parameter to your method.
//[error]    |
//[error]    |The ExecutionContext is used to configure how and on which
//[error]    |thread pools asynchronous tasks (such as Futures) will run,
//[error]    |so the specific ExecutionContext that is selected is important.
//[error]    |
//[error]    |If your application does not define an ExecutionContext elsewhere,
//[error]    |consider using Scala's global ExecutionContext by defining
//[error]    |the following:
//[error]    |
//[error]    |implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
//[error]    |
//[error]    |The following import might fix the problem:
//[error]    |
//[error]    |  import scala.concurrent.ExecutionContext.Implicits.globa

  def main(args: Array[String]): Unit =
    val result1: Int = Await.result(Future { 1 + 2 }, 1.second)
    println(result1)
