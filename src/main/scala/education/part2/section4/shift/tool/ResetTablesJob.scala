package education.part2.section4.shift.tool

import scala.concurrent.Await
import scala.concurrent.duration.*
import education.part2.section4.shift.DIContainer
import education.part2.section4.shift.persistence.table.WorkTable

/**
 * user テーブルを作り直すジョブ（消してから作る）
 */
object ResetTables:
  def main(args: Array[String]): Unit =
    val table = DIContainer.getInstance(classOf[WorkTable])
    Await.result(EducationTableManager.dropTable(table),   30.seconds)  // あれば消す
    Await.result(EducationTableManager.createTable(table), 30.seconds)  // 作り直す
    println("[OK] Work テーブルを作り直しました")
