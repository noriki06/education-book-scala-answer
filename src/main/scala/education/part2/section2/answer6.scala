package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer6:

  case class Member(
    id: Option[Member.Id],
    name: String,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr
    type WithNoId = Entity.WithNoId[Id, Member]
    type EmbeddedId = Entity.EmbeddedId[Id, Member]

  def main(args: Array[String]): Unit =
    Member(id = None, name = "Alice").toWithNoId         // apply で生成
    val draft = Member(id = None, name = "Alice").toWithNoId
    val saved = draft.v.copy(id = Some(Member.Id(1L))).toEmbeddedId

    println(saved.id.asString)
