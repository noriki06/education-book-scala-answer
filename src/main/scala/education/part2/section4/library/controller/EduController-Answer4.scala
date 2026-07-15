package education.part2.section4.library.controller

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ ExecutionContext, Future }
// import scala.concurrent.duration.*
import ixias.core.model.*
// import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Book
import education.part2.section4.library.model.Loan
import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer4:
  def main(args: Array[String]): Unit =
    println()

/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer4Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  enum ErrorType:
    case NonId
    case NonBook


  def lend(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[ErrorType, Loan.Id]] =
      edu.book.find(bookId).flatMap:
        found => found match
          case None       => Future.successful(Left(ErrorType.NonId))
          case Some(book) => book.v.state match
            case Book.State.OnLoan    => Future.successful(Left(ErrorType.NonBook))
            case Book.State.Available => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.OnLoan))
                                         for
                                           a <- edu.book.update(restate)
                                           loanId <- edu.loan.add(
                                             Loan(None, Loan.Status.Rent, user, Loan.Result.Success, book.v.title, date).toWithNoId)
                                         yield Right(loanId)

  def returnBook(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[ErrorType, Loan.Id]] =
      edu.book.find(bookId).flatMap:
        found => found match
          case None       => Future.successful(Left(ErrorType.NonId))
          case Some(book) => book.v.state match
            case Book.State.Available    => Future.successful(Left(ErrorType.NonBook))
            case Book.State.OnLoan => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.Available))
                                         for
                                           a <- edu.book.update(restate)
                                           loanId <- edu.loan.add(
                                             Loan(None, Loan.Status.Return, user, Loan.Result.Success, book.v.title, date).toWithNoId)
                                         yield Right(loanId)
