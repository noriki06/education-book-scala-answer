package education.part2.section4.shift.tool

import scala.concurrent.Await
import scala.concurrent.duration.*
import education.part2.section4.shift.DIContainer
import education.part2.section4.shift.persistence.table.WorkTable

/**
 * user テーブルを作成するジョブ
 */
object CreateTables:
  def main(args: Array[String]): Unit =
    // 04章の DI から UserTable を取り出す（new はしない）
    val table = DIContainer.getInstance(classOf[WorkTable])

    // ① まず実行される CREATE 文を確認（DB は変えない）
    Await.result(EducationTableManager.showCreateTable(table), 30.seconds)

    // ② テーブルを作成（既にあれば何もしない）
    Await.result(EducationTableManager.createTable(table), 30.seconds)

    println("[OK] Work テーブルを作成しました")
