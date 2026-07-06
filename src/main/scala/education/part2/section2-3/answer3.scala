package education.part2.section2-3

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer3:

  case class Member(
    name: String,
    age: Option[Int]
  )

  val members = List(
    Member("Alice", Some(30)),
    Member("Bob",   None),
    Member("Carol", Some(25)),
  )

  def main(args: Array[String]): Unit =
    println(members.sortBy(_.age)(using summon[Ordering[Int]].noneLastAsc))
