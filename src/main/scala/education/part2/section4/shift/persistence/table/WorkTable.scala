package education.part2.section4.shift.persistence.table

import java.time.LocalDateTime
import javax.inject.{ Inject, Singleton }
import slick.jdbc.JdbcProfile
import ixias.core.model.*
import ixias.core.persistence.HostSpec
import ixias.db.slick.{ SlickDatabaseContext, SlickTable }
import ixias.db.slick.backend.SlickDataSource
import education.part2.section4.shift.model.Work

/**
 * User エンティティ ⇄ `user` テーブル の対応表
 */
@Singleton
class WorkTable @Inject()(ctx: SlickDatabaseContext)
  extends SlickTable[Work.Id, Work, JdbcProfile](ctx):
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
  case class Table(tag: Tag) extends BasicTable(tag, "shift"):
    import Work.*

    @pk  def id        = column[Id]           ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
    @col def name      = column[String]       ("name",       O.Varchar(64))
    @col def planStart    = column[LocalDateTime] ("plan_start",      O.Varchar(191))
    @col def planEnd     = column[LocalDateTime] ("plan_end",      O.Varchar(191))
    @col def actualStart     = column[Option[LocalDateTime]]       ("actual_start",      O.Timestamp)
    @col def actualEnd = column[Option[LocalDateTime]]("actual_end", O.Timestamp)
    @col def status = column[Work.Status]      ("status",  O.UInt8)
    @col def createdAt = column[LocalDateTime]("created_at", O.Timestamp)
    @col def updatedAt = column[LocalDateTime]      ("updated_at",  O.Timestamp)

    def * = deriveColumns.mapTo[Work](onWrite = _.copy(updatedAt = LocalDateTime.now))
