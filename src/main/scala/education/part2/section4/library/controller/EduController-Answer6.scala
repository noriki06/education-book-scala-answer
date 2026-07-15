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
object Answer6:
  def main(args: Array[String]): Unit =
    val controller = DIContainer.getInstance(classOf[Answer6Controller])
    println(Await.result(controller.invoke(), 60.seconds))
/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer6Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  enum Error:
    case NonId
    case NonBook

  def lend(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[Error, Loan.Id]] =
      edu.book.find(bookId).flatMap:
        found => found match
          case None       => Future.successful(Left(Error.NonId))
          case Some(book) => book.v.state match
            case Book.State.OnLoan    => Future.successful(Left(Error.NonBook))
            case Book.State.Available => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.OnLoan))
                                         for
                                           a <- edu.book.update(restate)
                                           loanId <- edu.loan.add(
                                             Loan(None, Loan.Status.Rent, user, Loan.Result.Success, book.v.title, date).toWithNoId)
                                         yield Right(loanId)

  def returnBook(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[Error, Loan.Id]] =
      edu.book.find(bookId).flatMap:
        found => found match
          case None       => Future.successful(Left(Error.NonId))
          case Some(book) => book.v.state match
            case Book.State.Available    => Future.successful(Left(Error.NonBook))
            case Book.State.OnLoan => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.Available))
                                         for
                                           a <- edu.book.update(restate)
                                           loanId <- edu.loan.add(
                                             Loan(None, Loan.Status.Return, user, Loan.Result.Success, book.v.title, date).toWithNoId)
                                         yield Right(loanId)
  def invoke(): Future[Seq[Either[Error, Loan.Id]]] =
    for
      // ① 追加：WithNoId を渡し、採番された ID を受け取る
      idScala <- edu.book.add(Book(None, "Scala入門",        Book.Category.Technical, Book.State.Available).toWithNoId)
      idConan <- edu.book.add(Book(None, "名探偵コナン1",    Book.Category.Manga,     Book.State.Available).toWithNoId)
      idNeko  <- edu.book.add(Book(None, "吾輩は猫である",   Book.Category.Novel,     Book.State.Available).toWithNoId)
      idJamp  <- edu.book.add(Book(None, "週刊ジャンプ",     Book.Category.Magazine,  Book.State.Available).toWithNoId)
      idRefa  <- edu.book.add(Book(None, "リファクタリング", Book.Category.Technical, Book.State.Available).toWithNoId)

      loan1 <- lend(idScala, "Alice", LocalDateTime.of(2026,1,10,0,0))
      loan2 <- lend(idConan, "Bob",   LocalDateTime.of(2026,1,12,0,0))
      loan3 <- returnBook(idScala, "Alice", LocalDateTime.of(2026,1,20,0,0))
      loan4 <- lend(idScala, "Carol", LocalDateTime.of(2026,2,3,0,0))
      loan5 <- lend(idScala, "Dave",  LocalDateTime.of(2026,2,8,0,0))
      loan6 <- returnBook(idScala, "Carol", LocalDateTime.of(2026,2,15,0,0))
      loan7 <- lend(idNeko,  "Alice", LocalDateTime.of(2026,2,20,0,0))
      loan8 <- returnBook(idConan, "Bob",   LocalDateTime.of(2026,3,5,0,0))
      loan9 <- lend(idScala, "Bob",   LocalDateTime.of(2026,3,10,0,0))

    yield Seq(loan1, loan2, loan3, loan4, loan5, loan6, loan7, loan8, loan9)

  def toal(loanIds: Future[Seq[Either[Error, Loan.Id]]]): Map(Loan.Id, Int) =
    val rentSeq loanIds.collect { case Right(v) => v}.filter(_.status == Loan.Status.Rent)
    rentSeq.groupBy()
