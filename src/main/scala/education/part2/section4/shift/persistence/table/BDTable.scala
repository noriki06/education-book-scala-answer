package education.part2.section4.shift.persistence.table

import java.time.LocalDateTime
import javax.inject.{ Inject, Singleton }
import slick.jdbc.JdbcProfile
import ixias.core.model.*
import ixias.core.persistence.HostSpec
import ixias.db.slick.{ SlickDatabaseContext, SlickTable }
import ixias.db.slick.backend.SlickDataSource
import education.part2.section4.shift.model.BD

/**
 * User エンティティ ⇄ `user` テーブル の対応表
 */
@Singleton
class BDTable @Inject()(ctx: SlickDatabaseContext)
  extends SlickTable[BD.Id, BD, JdbcProfile](ctx):
  import api.{ *, given }

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
  case class Table(tag: Tag) extends BasicTable(tag, "BD"):
    import BD.*

    @pk  def id        = column[Id]           ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
    @col def actualStart = column[LocalDateTime]("actual_start", O.Timestamp)
    @col def actualEnd = column[LocalDateTime]("actual_End", O.Timestamp)
    @col def allTime = column[LocalDateTime]("all_time", O.Timestamp)
    @col def overTime = column[LocalDateTime]("over_time", O.Timestamp)
    @col def lateNight = column[LocalDateTime]("late_night", O.Timestamp)


    // 行 ⇄ User の相互変換。書き込みのたびに updatedAt を現在時刻にする
    def * = deriveColumns.mapTo[BD](onWrite = _.copy(updatedAt = LocalDateTime.now))
