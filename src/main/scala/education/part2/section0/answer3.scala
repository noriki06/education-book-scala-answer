package education.part2.section0

import ixias.core.model.*

object Answer3:

  case class Member(
    id:        Option[Member.Id],
    name:      String,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr

  def main(args: Array[String]): Unit =
    println(Member(None, "Alice").hasId)
    println(Member(None, "Alice").toDebugString)
