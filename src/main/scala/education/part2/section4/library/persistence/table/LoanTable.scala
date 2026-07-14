package education.part2.section4.library.persistence.table

import java.time.LocalDateTime
import javax.inject.{ Inject, Singleton }
import slick.jdbc.JdbcProfile
import ixias.core.model.*
import ixias.core.persistence.HostSpec
import ixias.db.slick.{ SlickDatabaseContext, SlickTable }
import ixias.db.slick.backend.SlickDataSource
import education.part2.section4.library.model.Loan
import education.part2.section4.library.model.Book


/**
 * User エンティティ ⇄ `user` テーブル の対応表
 */
@Singleton
class LoanTable @Inject()(ctx: SlickDatabaseContext)
  extends SlickTable[Loan.Id, Loan, JdbcProfile](ctx):
  import api.*

  // --[ データソース ]--------------------------------------------------
  // どの接続を使うか。DSN は "path://hostspec/database" 形式（02章の application.conf と対応）
  // 書き込みは PRIMARY、読み取り（find/filter）は REPLICA へ向けられるよう両方登録する
  val ds = Map(
    HostSpec.PRIMARY -> SlickDataSource("ixias.db://primary/education"),
    HostSpec.REPLICA -> SlickDataSource("ixias.db://replica/education")
  )

  // --[ テーブルクエリ ]------------------------------------------------
  val query = TableQuery[Table]

  // --[ テーブル定義 ]--------------------------------------------------
  case class Table(tag: Tag) extends BasicTable(tag, "book_loan_log"):
    import Loan.*

    @pk  def id        = column[Id]           ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
    @col def status     = column[Loan.Status] ("status",      O.UInt8)
    @col def user  = column[String]           ("user",   O.Varchar(64))
    @col def loanResult = column[Loan.Result] ("loan_result", O.UInt8)
    @col def bookTitle    = column[Title]      ("book_title", O.Varchar(64))
    @col def loanedAt = column[LocalDateTime] ("loaned_at", O.Timestamp)
    @col def updatedAt = column[LocalDateTime]("updated_at", O.Timestamp(onUpdate = true))
    @col def createdAt = column[LocalDateTime]("created_at", O.Timestamp)

    // 行 ⇄ User の相互変換。書き込みのたびに updatedAt を現在時刻にする
    def * = deriveColumns.mapTo[Loan](onWrite = _.copy(updatedAt = LocalDateTime.now))
