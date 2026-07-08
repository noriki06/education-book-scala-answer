package education.part2.section0

import ixias.core.model.*

object Answer6:

  case class Member(
    id:        Option[Member.Id],
    name:      String,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr
    type WithNoId = Entity.WithNoId[Id, Member]
    type EmbeddedId = Entity.EmbeddedId[Id, Member]

  def main(args: Array[String]): Unit =
    val draft = Member(id = None, name = "Alice").toWithNoId
    val saved = draft.v.copy(id = Some(Member.Id(1L))).toEmbeddedId


    println(saved.id.asString)
