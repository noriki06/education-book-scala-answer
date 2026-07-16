package education.part2.section4.library.tool

import scala.concurrent.Await
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.persistence.table.BookTable
import education.part2.section4.library.persistence.table.LoanTable

/**
 * user テーブルを作り直すジョブ（消してから作る）
 */
object ResetTables:
  def main(args: Array[String]): Unit =
    val bookTable = DIContainer.getInstance(classOf[BookTable])
    Await.result(EducationTableManager.dropTable(bookTable),   30.seconds)  // あれば消す
    Await.result(EducationTableManager.createTable(bookTable), 30.seconds)  // 作り直すi
    val loanTable = DIContainer.getInstance(classOf[LoanTable])
    Await.result(EducationTableManager.dropTable(loanTable),   30.seconds)  // あれば消す
    Await.result(EducationTableManager.createTable(loanTable), 30.seconds)  // 作り直す

    println("[OK] user テーブルを作り直しました")
