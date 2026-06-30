package education.section0

object AnswerEx85:

  case class User(
    id: Int,
    name: String
  )

  def main(args: Array[String]): Unit =
    println(forEx4(Seq(User(1, "Alice"), User(2, "Bob")), 1, 2))

  def forEx4(users: Seq[User], id1: Int, id2: Int): Option[String] =
    for {
      a <- users.find(user => user.id == id1)
      b <- users.find(user => user.id == id2)
    } yield s"${a.name}さんと${b.name}さん"
