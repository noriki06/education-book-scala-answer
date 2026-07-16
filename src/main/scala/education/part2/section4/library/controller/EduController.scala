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
object Answer3:
  def main(args: Array[String]): Unit =
    val controller = DIContainer.getInstance(classOf[Answer3Controller])
    println(Await.result(controller.invoke(), 60.seconds))


/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer3Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  /**
   * 本idの取得メソッド
   */
  def invoke(): Future[Seq[Book.Id]] =  /** add → find → update → delete を 1 本の流れで実行する */

    val books =
      Seq(
        Book(None, "Scala入門",        Book.Category.Technical, Book.State.Available).toWithNoId,
        Book(None, "名探偵コナン1",    Book.Category.Manga,     Book.State.Available).toWithNoId,
        Book(None, "吾輩は猫である",   Book.Category.Novel,     Book.State.Available).toWithNoId,
        Book(None, "週刊ジャンプ",     Book.Category.Magazine,  Book.State.Available).toWithNoId,
        Book(None, "リファクタリング", Book.Category.Technical, Book.State.Available).toWithNoId
      )

    for {
      ids <- Future.sequence(books.map(edu.book.add))
    } yield ids
