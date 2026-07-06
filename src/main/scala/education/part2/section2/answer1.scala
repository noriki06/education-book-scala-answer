package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer1:

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr                    // User.Id という ID 型に名前を付ける

  def main(args: Array[String]): Unit =
    val id: Member.Id = MemberId(1001L)         // apply で生成

    println(Member.Id(1001L).value)
    println(Member.Id(1001L).asString)
