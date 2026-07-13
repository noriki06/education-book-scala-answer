package education.part2.section4.library.persistence.table

import java.time.LocalDateTime
import javax.inject.{ Inject, Singleton }
import slick.jdbc.JdbcProfile
import ixias.core.model.*
import ixias.core.persistence.HostSpec
import ixias.db.slick.{ SlickDatabaseContext, SlickTable }
import ixias.db.slick.backend.SlickDataSource
import education.part2.section4.library.model.Book

/**
 * User エンティティ ⇄ `user` テーブル の対応表
 */
@Singleton
class BookTable @Inject()(ctx: SlickDatabaseContext)
  extends SlickTable[Book.Id, Book, JdbcProfile](ctx):
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
  case class Table(tag: Tag) extends BasicTable(tag, "book"):
    import Book.*

    @pk  def id        = column[Id]           ("id",         O.UInt64, O.PrimaryKey, O.AutoInc)
    @col def title     = column[String] ("status",      O.Varchar(64))
    @col def category  = column[Book.Category] ("category", O.UInt8)
    @col def state    = column[Book.State]      ("state",  O.UInt8)
    @col def updatedAt = column[LocalDateTime]("updated_at", O.Timestamp(onUpdate = true))
    @col def createdAt = column[LocalDateTime]("created_at", O.Timestamp)

    // 行 ⇄ User の相互変換。書き込みのたびに updatedAt を現在時刻にする
    def * = deriveColumns.mapTo[Book](onWrite = _.copy(updatedAt = LocalDateTime.now))
