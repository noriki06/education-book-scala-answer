package education.part2.section2

import ixias.core.model.*
object Answer5:

  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr

  def main(args: Array[String]): Unit =
    val id: Member.Id = Member.Id(1001L)

    println(Member.Id(1001L).value)
    println(Member.Id(1001L).asString)
