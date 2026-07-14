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


    for {
      idScala <- edu.book.add(scala)
      idConan <- edu.book.add(conan)
      idNeko <- edu.book.add(neko)
      idJamp <- edu.book.add(jamp)
      idRefa <- edu.book.add(refa)

      idloan1 <- pedu.book.add(loan1)
      idloan2 <- pedu.book.add(loan2)
      idloan3 <- pedu.book.add(loan3)
      idloan4 <- pedu.book.add(loan4)
      idloan5 <- pedu.book.add(loan5)
      idloan6 <- pedu.book.add(loan6)
      idloan7 <- pedu.book.add(loan7)
      idloan8 <- pedu.book.add(loan8)
      idloan9 <- pedu.book.add(loan9)

      fScala <- edu.user.find(idScala)
      fConan <- edu.user.find(idConan)
      fNeko <- edu.user.find(idNeko)
      fJamp <- edu.user.find(idJamp)
      fRefa <- edu.user.find(idRefa)

      reloan1 <-



              before  <- found match
                   case Some(u) => edu.user.update(u.map(_.copy(state = User.Status.Withdrawn)))
                   case None    => Future.successful(None)
      _        = println(s"更新：更新前の状態 = ${before.map(_.v.state)}")


貸出：指定した蔵書を、指定した利用者に貸し出す。ただし すでに貸出中の蔵書は貸し出せない（二重貸出の禁止）。成功したら、蔵書を「貸出中」に更新し、貸出ログに「貸出」を 1 件追記する。貸し出せない場合は、その理由が呼び出し側に分かる こと（状態も履歴も変化させない）。
返却：指定した蔵書を返却する。蔵書を「貸出可能」に戻し、貸出ログに「返却」を 1 件追記する。
どちらの操作も、発生日時を指定できること（過去日時での記録も可能にする。問5 のシナリオで必要）。
      theScala <-
      theConan <-
      theNeko <-
      theJamp <-
      theRefa <-

    } yield ids
