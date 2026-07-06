package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object JoinAnswer1:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None
  ) extends JoinSyntax[Member]

    given JoinWith[Member, Group] =
      (member, groups) =>
        member.copy(groupName = groups.find(_.id == member.groupId).map(_.name))


  case class Group(
    id: Int,
    name: String
  )

  def main(args: Array[String]): Unit =

    println(Member(1, "Alice", 10).join(Seq(Group(10, "営業"), Group(20, "開発"))))
