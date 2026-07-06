package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer2:

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr  // User.Id という ID 型に名前を付ける

  object Item:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr

  def main(args: Array[String]): Unit =
    val memId: Member.Id = Member.Id(1L)
    val itemId: Item.Id = Item.Id(1L)

    println(Member.Id(1L) === Member.Id(1L))
    // println(Member.Id(1L) === Item.Id(1L))  コンパイルエラーを確認
