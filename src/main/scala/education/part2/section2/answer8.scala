package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も


object Answer8:

  case class Member(
    id: Option[Member.Id],
    name: String,
    email: EmailAddress,
    state: Member.Status,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr
    type WithNoId = Entity.WithNoId[Id, Member]
    type EmbeddedId = Entity.EmbeddedId[Id, Member]

    enum Status(val code: Int) extends EnumStatus[Int]:
      case Active    extends Status(1) // 有効
      case Suspended extends Status(2) // 一時停止中
      case Withdrawn extends Status(3) // 退会


  def main(args: Array[String]): Unit =
    val first: Member.WithNoId =
      Member(
        id = None,
        name = "Alice",
        email = EmailAddress("alice@example.com"),
        state = Member.Status.Active
      ).toWithNoId

    val member = first.v.copy(id = Some(Member.Id(1L))).toEmbeddedId

    val withdrawn = member.map(_.copy(state = Member.Status.Withdrawn))

    println(withdrawn.id.asString)
    println(withdrawn.v.email)
    println(withdrawn.v.state)
