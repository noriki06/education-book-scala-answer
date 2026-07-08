package education.part2.section0

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer18:

  case class Member(
    name: String,
    lastLoginAt: Option[LocalDateTime]
  )

  def main(args: Array[String]): Unit =
    val members = List(
      Member("alice", Some(LocalDateTime.of(2026, 7, 6, 10, 0))),
      Member("bob", Some(LocalDateTime.of(2025, 6, 6, 10, 0))),
      Member("noriki", None)
    )


    println(members.sortBy(_.lastLoginAt)(using summon[Ordering[LocalDateTime]].noneLastDesc))
