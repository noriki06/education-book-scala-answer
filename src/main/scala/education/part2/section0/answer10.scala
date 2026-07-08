package education.part2.section0

import ixias.core.model.syntax.*
object Answer10:

  case class Member(
    id: Int,
    name: String,
    postCount: Int = 0
  ) extends JoinSyntax[Member]

  case class Post(
    id: Int,
    memberId: Int,
    title: String
  )

  object Member:
    given JoinWith[Member, Post] =
      (member, posts) =>
        member.copy(postCount = posts.count(_.memberId == member.id))

  def main(args: Array[String]): Unit =
    val posts = Seq(
      Post(1, 1, "aaa"),
      Post(2, 1, "bbbb"),
      Post(3, 1, "ccc"),
      Post(4, 2, "ddd")
    )

    println(Member(1, "Alice").join(posts))
