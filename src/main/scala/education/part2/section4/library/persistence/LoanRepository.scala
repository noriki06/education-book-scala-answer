package education.part2.section4.library.persistence

import javax.inject.{ Inject, Singleton }
import ixias.db.slick.{ SlickBaseRepository, SlickDatabaseContext }
import education.part2.section4.library.persistence.table.LoanTable

/**
 * Loan の保存・取得の窓口。
 * find / filter / add / update / delete は SlickBaseRepository が
 * 自動で提供する（実装を書く必要はない）。
 */
@Singleton
class LoanRepository @Inject()(table: LoanTable, ctx: SlickDatabaseContext)
  extends SlickBaseRepository(table, ctx)
