package education.part2.section4.library.controller

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.model.Loan
import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer7:
  def main(args: Array[String]): Unit =
    val ans7C = DIContainer.getInstance(classOf[Answer7Controller])
    println(Await.result(ans7C.totalMonth(), 60.seconds))

@Singleton
class Answer7Controller @Inject()
  (edu: EduRepositoryFacade, ans5C: Answer5Controller)(using ExecutionContext):
  /**
   * 月ごとの貸出件数メソッド
   */
  def totalMonth(): Future[Seq[(Int, Int)]] =
    for
      seqId <- ans5C.invoke()
      onlysec = seqId.collect { case Right(v) => v }
      loans <- edu.loan.filter(onlysec)
    yield
      loans
        .filter(_.v.status == Loan.Status.Rent)
        .groupBy(_.v.loanedAt.getMonthValue)
        .view
        .mapValues(_.size)
        .toSeq
        .sorted
