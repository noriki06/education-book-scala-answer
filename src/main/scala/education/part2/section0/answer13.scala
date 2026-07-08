package education.part2.section0

import ixias.core.model.syntax.*

object Answer13:

  case class Member(
    id: Int,
    name: String,
    groupId: Int,
    groupName: Option[String] = None,
    postCount: Int = 0
  ) extends JoinSyntax[Member]

  case class Group(
    id: Int,
    name: String
  )

  case class Post(
    id: Int,
    memberId: Int,
    title: String
  )

  object Member:
    given JoinWith[Member, Group] =
      (member, groups) =>
        member.copy(groupName = groups.find(_.id == member.groupId).map(_.name))

    given JoinWith[Member, Post] =
      (member, posts) =>
        member.copy(postCount = posts.count(_.memberId == member.id))


  def main(args: Array[String]): Unit =
    val members = Seq(
      Member(1, "Alice", 10),
      Member(2, "Bob", 20)
    )

    val allGroups = Seq(
      Group(10, "営業"),
      Group(20, "開発")
    )

    val allPosts = Seq(
      Post(10, 1, "これ"),
      Post(20, 1, "それ"),
      Post(30, 1, "あれ"),
      Post(40, 2, "違う")
    )

    println(members.map(_.join(allGroups).join(allPosts)))
