package education.part2.section0

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer16:

  case class Member(
    name: String,
    age: Option[Int]
  )

  def main(args: Array[String]): Unit =
    val members =
      Seq(
        Member("Alice", Some(30)),
        Member("Bob", None),
        Member("Carol", Some(25))
      )

    println(members.sortBy(_.age)(using summon[Ordering[Int]].noneLastAsc))
