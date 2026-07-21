package education.part2.section4.library.controller

import scala.concurrent.{ Await, ExecutionContext, Future }
import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Book
import education.part2.section4.library.model.Loan
import scala.concurrent.ExecutionContext.Implicits.global
import ixias.core.model.*
import scala.concurrent.duration.*


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer5:
  def main(args: Array[String]): Unit =
    val ans3C = DIContainer.getInstance(classOf[Answer3Controller])
    val bookIds = Await.result(ans3C.invoke(), 60.seconds)
    val loans = invoke(bookIds)

    println("[OK] demo 完了")

  /**
   * 貸し出し表のメソッド
   */
  def invoke(bookIds: Seq[Book.Id])(using ExecutionContext): Future[Seq[Either[Book.ErrorType, Loan.Id]]] =
    val ans4C = DIContainer.getInstance(classOf[Answer4Controller])

    val books = edu.book.filter(bookIds).map {
      case Some(book) => book.title
      case None       => println("見つかりませんでした")
    }

    val results =
      for
        loan1 <- ans4C.lend(idScala, "Alice", LocalDateTime.of(2026,1,10,0,0))
        loan2 <- ans4C.lend(idConan, "Bob",   LocalDateTime.of(2026,1,12,0,0))
        loan3 <- ans4C.returnBook(idScala, "Alice", LocalDateTime.of(2026,1,20,0,0))
        loan4 <- ans4C.lend(idScala, "Carol", LocalDateTime.of(2026,2,3,0,0))
        loan5 <- ans4C.lend(idScala, "Dave",  LocalDateTime.of(2026,2,8,0,0))
        loan6 <- ans4C.returnBook(idScala, "Carol", LocalDateTime.of(2026,2,15,0,0))
        loan7 <- ans4C.lend(idNeko,  "Alice", LocalDateTime.of(2026,2,20,0,0))
        loan8 <- ans4C.returnBook(idConan, "Bob",   LocalDateTime.of(2026,3,5,0,0))
        loan9 <- ans4C.lend(idScala, "Bob",   LocalDateTime.of(2026,3,10,0,0))
      yield Seq(loan1, loan2, loan3, loan4, loan5, loan6, loan7, loan8, loan9)

    results
