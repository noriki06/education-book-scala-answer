package education.part2.section2-2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer1:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None
  ) extends JoinSyntax[Member]

  object Member:
    object Id extends EntityId[Long]     // Long を土台にした「会員IDの工場」
    type Id = Id.Repr                    // User.Id という ID 型に名前を付ける

    given JoinWith[Member, Group] =
      (member, groups) =>
        member.copy(groupName = groups.find(_.id == member.groupId).map(_.name))


  case class Group(
    id: Int,
    name: String
  )

  def main(args: Array[String]): Unit =

    println(Member(1, "Alice", 10).join(Seq(Group(10, "営業"), Group(20, "開発"))))
