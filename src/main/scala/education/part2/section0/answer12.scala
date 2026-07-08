package education.part2.section0

import ixias.core.model.syntax.*

object Answer12:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None
  ) extends JoinSyntax[Member]

  object Member:
    given JoinWithEither[Member, Group, String] =
      (member, groups) =>
        groups.find(_.id == member.groupId) match
          case Some(g) => Right(member.copy(groupName = Some(g.name)))
          case None    => Left(s"group not found: groupId=${member.groupId}")

  case class Group(
    id: Int,
    name: String
  )

  def main(args: Array[String]): Unit =
    println(Member(1, "Alice", 10).joinEither(Seq(Group(10, "営業"))))
    println(Member(1, "Alice", 99).joinEither(Seq(Group(10, "営業"))))
