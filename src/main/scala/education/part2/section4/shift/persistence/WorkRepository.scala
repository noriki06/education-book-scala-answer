package education.part2.section4.shift.persistence

import javax.inject.{ Inject, Singleton }
import ixias.db.slick.{ SlickBaseRepository, SlickDatabaseContext }
import education.part2.section4.shift.persistence.table.WorkTable


@Singleton
class WorkRepository @Inject()(table: WorkTable, ctx: SlickDatabaseContext)
  extends SlickBaseRepository(table, ctx)
