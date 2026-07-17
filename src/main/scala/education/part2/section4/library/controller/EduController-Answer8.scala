package education.part2.section4.library.controller
import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Book
import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer8:
  def main(args: Array[String]): Unit =
    val ans3C = DIContainer.getInstance(classOf[Answer3Controller])
    val ans5C = DIContainer.getInstance(classOf[Answer5Controller])
    val ans6C = DIContainer.getInstance(classOf[Answer6Controller])
    val ans7C = DIContainer.getInstance(classOf[Answer7Controller])
    val ans8C = DIContainer.getInstance(classOf[Answer8Controller])

    println(Await.result(ans5C.invoke(), 60.seconds))
    println(Await.result(ans6C.totalBook(), 60.seconds))
    println(Await.result(ans6C.neverLend(), 60.seconds))
    println(Await.result(ans7C.totalMonth(), 60.seconds))
    println(Await.result(ans8C.invoke(), 60.seconds))

@Singleton
class Answer8Controller @Inject()
(edu: EduRepositoryFacade)(using ExecutionContext):
  /**
   * 各蔵書の「タイトル・カテゴリ・現在貸出中か」を出す。
   */
  def invoke(): Future[Seq[(String, Book.Category, Book.State)]] =
    for
      bookIds <- ans3C.invoke()
      books <- edu.book.filter(bookIds)
    yield books.map(book => (book.v.title, book.v.category, book.v.state))
