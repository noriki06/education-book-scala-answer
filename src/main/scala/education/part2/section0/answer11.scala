package education.part2.section0

import ixias.core.model.syntax.*

object Answer11:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None,
    postCount: Int = 0
  ) extends JoinSyntax[Member]

  object Member:
    given JoinWith[Member, Group] =
      (member, groups) =>
        member.copy(groupName = groups.find(_.id == member.groupId).map(_.name))

    given JoinWith[Member, Post] =
      (member, posts) =>
        member.copy(postCount = posts.count(_.memberId == member.id))

  case class Group(
    id: Int,
    name: String
  )

  case class Post(
    id: Int,
    memberId: Int,
    title: String
  )

  def main(args: Array[String]): Unit =
    val allPosts = Seq(
      Post(1, 1, "aaa"),
      Post(2, 1, "bbbb"),
      Post(3, 1, "ccc"),
      Post(4, 2, "ddd")
    )

    val allGroups = Seq(
      Group(10, "営業"),
      Group(20, "開発")
    )

    println(Member(1, "Alice", 10).join(allGroups).join(allPosts))
