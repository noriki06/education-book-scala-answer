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
  def invoke(): Future[Seq[Book.Id]] =  /** add → find → update → delete を 1 本の流れで実行する */

    val scala = (Book(None, "Scala入門",        Book.Category.Technical, Book.State.Available)).toWithNoId,
    val conan = (Book(None, "名探偵コナン1",    Book.Category.Manga,     Book.State.Available)).toWithNoId,
    val neko = (Book(None, "吾輩は猫である",   Book.Category.Novel,     Book.State.Available)).toWithNoId,
    val jamp = (Book(None, "週刊ジャンプ",     Book.Category.Magazine,  Book.State.Available)).toWithNoId,
    val refa = (Book(None, "リファクタリング", Book.Category.Technical, Book.State.Available)).toWithNoId

    val loan1 = (Loan(None, Loan.Status.Rent, "Alice", Loan.Result.Success, "Scala入門", LocalDateTime.of(2026,1,10,0,0))).toWithNoId,
    val loan2 = (Loan(None, Loan.Status.Rent, "Bob", Loan.Result.Success, "名探偵コナン1", LocalDateTime.of(2026,1,12,0,0))).toWithNoId,
    val loan3 = (Loan(None, Loan.Status.Return, "Alice", Loan.Result.Success,"Scala入門",  LocalDateTime.of(2026,1,20,0,0))).toWithNoId,
    val loan4 = (Loan(None, Loan.Status.Rent, "Carol", Loan.Result.Success, "Scala入門", LocalDateTime.of(2026,2,3,0,0))).toWithNoId,
    val loan5 = (Loan(None, Loan.Status.Rent, "Dave", Loan.Result.Failure, "Scala入門", LocalDateTime.of(2026,2,8,0,0))).toWithNoId,
    val loan6 = (Loan(None, Loan.Status.Return, "Carol", Loan.Result.Success, "Scala入門", LocalDateTime.of(2026,2,15,0,0))).toWithNoId,
    val loan7 = (Loan(None, Loan.Status.Rent, "Alice", Loan.Result.Success, "吾輩は猫である", LocalDateTime.of(2026,2,20,0,0))).toWithNoId,
    val loan8 = (Loan(None, Loan.Status.Rent, "Bob", Loan.Result.Success, "名探偵コナン1", LocalDateTime.of(2026,3,5,0,0))).toWithNoId,
    val loan9 = (Loan(None, Loan.Status.Rent, "Bob", Loan.Result.Success, "Scala入門", LocalDateTime.of(2026,3,10,0,0)).toWithNoId

    val books = Seq(scala, conan, neko, jamp, refa)
    val loans = Seq(loan1, loan2, loan3, loan4, loan5, loan6, loan7, loan8, loan9)

    for {
      idbooks <- Future.sequence(books.map(edu.book.add))
      idloans <- Future.sequence(loans.map(edu.loan.add))
    } yield ids


  enum Error:
    case NonId
    case NonBook


  def lend(bookId: Book.Id, user: String, date: LocalDateTime):
    Future[Either[Error, Option[Loan.EmbeddedId]]] =
    edu.book.find(bookId).map match {
      case None       => Future.successful(Left(Error.NonId))
      case Some(book) => book.v.state match {
        case OnLoan    => Future.successful(Left(Error.NonBook))
        case Available => val restate: Book.EmbeddedId = book.map(_.copy(state = Book.State.OnLoan))
                          Right(
                            for {
                              a <- book.update(restate)
                              _ <- edu.loan.add(loans)
                            } yield a)
      }
    }


  def return(bookId: Book.Id):
    Future[Either[Error, Option[Loan.EmbeddedId]]] =
    for {
      a <- edu.book.find(bookId).update(book.map(_.copy(state = Book.State.Available)))
      b <- edu.book.add(Loans)
    } yield a
