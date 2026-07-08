package education.part2.section0

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer4:

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr

    enum Status(val code: Int) extends EnumStatus[Int]:
      case Active    extends Status(1)
      case Suspended extends Status(2)
      case Withdrawn extends Status(3)

  def main(args: Array[String]): Unit =
    println(Member.Status.of(2))
    println(Member.Status.find(99))
    println(Member.Status.Active.code)
