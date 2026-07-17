package education.part2.section4.library.controller
import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Loan
import education.part2.section4.library.model.Book
import education.part2.section4.library.persistence.EduRepositoryFacade

/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer6:
  def main(args: Array[String]): Unit =
    val ans6C = DIContainer.getInstance(classOf[Answer6Controller])
    val ans4C = DIContainer.getInstance(classOf[Answer4Controller])
    val ans3C = DIContainer.getInstance(classOf[Answer3Controller])
    val bookIds = ans3C.invoke()
    val loans = Answer5.invoke(bookIds)
    Await.result(ans6C.totalBook(loans), 60.seconds)
    Await.result(ans6C.neverLend(loans, bookIds), 60.seconds)
    println("[OK] demo 完了")

/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer6Controller @Inject()
(edu: EduRepositoryFacade)(using ExecutionContext):
  /**
   * 本ごとの貸し出し回数のメソッド
   */
  def totalBook(loans: Future[Seq[Either[Book.ErrorType, Loan.Id]]]): Future[Map[String, Int]] =
    for
      seqId <- loans
      onlysec = seqId.collect { case Right(v) => v }
      loans <- edu.loan.filter(onlysec)
    yield
      loans.filter(_.v.status == Loan.Status.Rent).groupBy(_.v.bookTitle).view.mapValues(_.size).toMap
  /**
   *
   */
  def neverLend(loans: Future[Seq[Either[Book.ErrorType, Loan.Id]]], bookIds: Future[Seq[Book.Id]]): Future[Seq[String]] =
    val allTitle =
      for
        ids <- bookIds
        books <- edu.book.filter(ids)
      yield books.map(_.v.title)

    val lendTitle =
      for
        seqId <- loans
        onlysec = seqId.collect { case Right(v) => v }
        loans <- edu.loan.filter(onlysec)
      yield
        loans.filter(_.v.status == Loan.Status.Rent).map(_.v.bookTitle)

    for
      all <- allTitle
      lend <- lendTitle
    yield all.diff(lend)
