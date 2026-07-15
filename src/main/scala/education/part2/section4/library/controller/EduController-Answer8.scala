package education.part2.section4.library.controller

import scala.concurrent.Await
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
// import education.part2.section4.library.model.Book
// import education.part2.section4.library.model.Loan
// import education.part2.section4.library.persistence.EduRepositoryFacade


/**
 * 入口（Play を使わない学習ジョブ）。
 * DI コンテナから EduController を 1 個取り出して invoke() を呼ぶだけ。
 */
object Answer8:
  def main(args: Array[String]): Unit =
    val ans5C = DIContainer.getInstance(classOf[Answer5Controller])
    val ans6C = DIContainer.getInstance(classOf[Answer6Controller])
    val ans7C = DIContainer.getInstance(classOf[Answer7Controller])

    println(Await.result(ans5C.invoke(), 60.seconds))
    println(Await.result(ans6C.totalBook(ans6C.invokeLoan()), 60.seconds))
    println(Await.result(ans6C.neverLend(ans6C.invokeLoan(), ans6C.invokeBook()), 60.seconds))
    println(Await.result(ans7C.totalMonth(ans7C.invoke()), 60.seconds))
