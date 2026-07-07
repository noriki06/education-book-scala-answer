package education.part2.section2

import ixias.core.model.syntax.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object JoinAnswer3:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None,
    postCount: Int = 0
  ) extends JoinSyntax[Member]

  case class Post(
    id: Int,
    memberId: Int,
    title: String
  )

  case class Group(
    id: Int,
    name: String
  )

  given JoinWith[Member, Post] =
    (member, posts) =>
      member.copy(postCount = posts.count(_.memberId == member.id))

  given JoinWith[Member, Group] =
      (member, groups) =>
        member.copy(groupName = groups.find(_.id == member.groupId).map(_.name))

  def main(args: Array[String]): Unit =
    val allGroups =Seq(
      Group(10, "営業"),
      Group(20, "開発")
    )

    val allPosts = Seq(
      Post(10, 1, "これ"),
      Post(20, 1, "それ"),
      Post(30, 1, "あれ"),
      Post(40, 2, "違う")
    )

    println(Member(1, "Alice", 10).join(allGroups).join(allPosts))
