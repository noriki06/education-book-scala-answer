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
    val table = DIContainer.getInstance(classOf[UserTable])

    // ① まず実行される CREATE 文を確認（DB は変えない）
    Await.result(EducationTableManager.showCreateTable(table), 30.seconds)

    // ② テーブルを作成（既にあれば何もしない）
    Await.result(EducationTableManager.createTable(table), 30.seconds)

    println("[OK] user テーブルを作成しました")
