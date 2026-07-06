package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer3:

  case class Member(
    id: Option[Member.Id],
    name: String,
    updatedAt: LocalDateTime = Now,
    createdAt: LocalDateTime = Now
  ) extends EntityModel[Member.Id]

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr                    // User.Id という ID 型に名前を付ける

  def main(args: Array[String]): Unit =
    Member(id = None, name = "Alice")         // apply で生成

    println(Member(None, "Alice").hasId)
    println(Member(None, "Alice").toDebugString)
