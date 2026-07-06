package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer5:

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

  def main(args: Array[String]): Unit =
    val draft = Member(id = None, name = "Alice").toWithNoId         // apply で生成

    println(draft.v.name)
