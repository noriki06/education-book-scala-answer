package education.part2.section4.library

import javax.inject.{ Inject, Singleton }
import ixias.core.inject.IxiasModule
import education.part2.section4.library.persistence.table.*   // UserTable を参照

package object persistence:

  /**
   * この永続化パッケージのテーブル・リポジトリを Guice に登録する
   */
  class Module extends IxiasModule:
    def bindings(): Unit =
      singleton[BookTable]              // テーブル
      singleton[LoanTable]
      singleton[BookRepository]  // リポジトリ
      singleton[LoanRepository]
      singleton[EduRepositoryFacade]    // 集約（下記）

  /**
   * リポジトリの集約。使う側はこれ 1 つを注入すれば各リポジトリに届く
   */
  @Singleton
  class EduRepositoryFacade @Inject()(
    val book: BookRepository,
    val loan: LoanRepository
  )
