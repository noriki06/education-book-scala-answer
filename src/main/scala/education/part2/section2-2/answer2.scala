package education.part2.section2

import ixias.core.model.syntax.*         // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object JoinAnswer2:

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

  given JoinWith[Member, Post] =
    (member, posts) =>
      member.copy(postCount = posts.count(_.memberId == member.id))

  def main(args: Array[String]): Unit =
    val posts = Seq(
      Post(10, 1, "これ"),
      Post(20, 1, "それ"),
      Post(30, 1, "あれ"),
      Post(40, 2, "違う")
    )

    println(Member(1, "Alice").join(posts))
