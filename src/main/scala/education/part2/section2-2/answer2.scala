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
    val id: Member.Id = Member.Id(1001L)         // apply で生成

    println(Member.Id(1001L).value)
    println(Member.Id(1001L).asString)
