package education.part2.section4.library.controller

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.*
import ixias.core.model.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Book
import education.part2.section4.library.model.Loan
import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer4:
  def main(args: Array[String]): Unit =
    val controller = DIContainer.getInstance(classOf[Answer4Controller])
    println(Await.result(controller.invoke(), 60.seconds))


/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer4Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  def invoke(): Future[Seq[Book.Id]] = ??? /** add → find → update → delete を 1 本の流れで実行する */


  enum Error:
    case NonId
    case NonBook


  def lend(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[Error, Option[Loan.EmbeddedId]]] =
      edu.book.find(bookId).flatMap {
        found => found match {
          case None       => Future.successful(Left(Error.NonId))
          case Some(book) => book.v.state match {
            case Book.State.OnLoan    => Future.successful(Left(Error.NonBook))
            case Book.State.Available => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.OnLoan))
                                         for {
                                           a <- edu.book.update(restate)
                                           loanId <- edu.loan.add
                                         } yield a
        }
      }
    }


  def returnBook(bookId: Book.Id):
    Future[Either[Error, Option[Loan.EmbeddedId]]] =
    for {
      a <- edu.book.find(bookId).update(book.map(_.copy(state = Book.State.Available)))
      b <- edu.book.add(Loans)
    } yield a
