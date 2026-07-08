package education.part2.section0

import ixias.core.model.*

object Answer7:

  case class Member(
    id:        Option[Member.Id],
    name:      String,
    state:     Member.Status,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr
    type WithNoId = Entity.WithNoId[Id, Member]
    type EmbeddedId = Entity.EmbeddedId[Id, Member]

    enum Status(val code: Int) extends EnumStatus[Int]:
      case Active    extends Status(1)
      case Suspended extends Status(2)
      case Withdrawn extends Status(3)

  def main(args: Array[String]): Unit =
    val saved = Member(id = Some(Member.Id(1L)), name = "Alice", state = Member.Status.Active).toEmbeddedId
    val suspended = saved.map(_.copy(state = Member.Status.Suspended))

    println(suspended.id.asString)
    println(suspended.v.state)
