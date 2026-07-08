package education.part2.section0

import ixias.core.model.*

object Answer2:

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr

  object Item:
    object Id extends EntityId[Long]
    type Id = Id.Repr

  def main(args: Array[String]): Unit =
    println(Member.Id(1L) === Member.Id(1L))
    // println(Member.Id(1L) === Item.Id(1L)) コンパイルエラー
