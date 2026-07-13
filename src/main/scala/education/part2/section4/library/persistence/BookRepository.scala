package education.part2.section4.library.persistence

import javax.inject.{ Inject, Singleton }
import ixias.db.slick.{ SlickBaseRepository, SlickDatabaseContext }
import education.part2.section4.library.persistence.table.BookTable

/**
 * Book の保存・取得の窓口。
 */
@Singleton
class BookRepository @Inject()(table: BookTable, ctx: SlickDatabaseContext)
  extends SlickBaseRepository(table, ctx)
