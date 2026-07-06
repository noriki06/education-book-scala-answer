package education.part2.section2-2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer2:

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
    println(Member(1, "Alice", 10)
      .join(
        Seq(
          Post(10, 1, "これ"),
          Post(20, 1, "それ"),
          Post(30, 1, "あれ"),
          Post(40, 2, "違う")
          )))
