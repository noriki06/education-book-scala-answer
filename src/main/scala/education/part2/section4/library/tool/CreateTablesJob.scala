package education.part2.section4.library.tool

import scala.concurrent.Await
import scala.concurrent.duration.*
import education.part2.section4.library.DIContainer
import education.part2.section4.library.persistence.table.BookTable
import education.part2.section4.library.persistence.table.LoanTable

/**
 * user テーブルを作成するジョブ
 */
object CreateTables:
  def main(args: Array[String]): Unit =
    // 04章の DI から UserTable を取り出す（new はしない）
    val bookTable = DIContainer.getInstance(classOf[BookTable])

    // ① まず実行される CREATE 文を確認（DB は変えない）
    Await.result(EducationTableManager.showCreateTable(bookTable), 30.seconds)

    // ② テーブルを作成（既にあれば何もしない）
    Await.result(EducationTableManager.createTable(bookTable), 30.seconds)

    val loanTable = DIContainer.getInstance(classOf[LoanTable])

    // ① まず実行される CREATE 文を確認（DB は変えない）
    Await.result(EducationTableManager.showCreateTable(loanTable), 30.seconds)

    // ② テーブルを作成（既にあれば何もしない）
    Await.result(EducationTableManager.createTable(loanTable), 30.seconds)

    println("[OK] user テーブルを作成しました")
