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
object Answer7:
  def main(args: Array[String]): Unit =
    val ans7C = DIContainer.getInstance(classOf[Answer7Controller])
    println(Await.result(controller.totalMonth(ans7C.totalMonth()), 60.seconds))
/**
 * 処理の入口クラス（Play で言うコントローラ相当）。
 * 依存はすべてコンストラクタ注入で受け取る（edu も ExecutionContext も注入された値）。
 */
@Singleton
class Answer7Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  def totalMonth(loanIds: Future[Seq[Either[Book.ErrorType, Loan.Id]]]): Future[Seq[(Int, Int)]] =
    for
      seqId <- loanIds
      onlysec = seqId.collect { case Right(v) => v}
      loans <- edu.loan.filter(onlysec)
    yield
      loans
        .filter(_.v.status == Loan.Status.Rent)
        .groupBy(_.v.loanedAt.getMonthValue)
        .view
        .mapValues(_.size)
        .toSeq
        .sorted
