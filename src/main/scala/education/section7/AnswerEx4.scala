package education.section7

object AnswerEx4:

  case class User(
    id: Int,
    name: String
  )

  def main(args: Array[String]): Unit =
    println(forEx4(users = Seq(User(1, "Alice"), User(2, "Bob")), id1 = 1, id2 = 2))
    println(forEx4(users = Seq(User(1, "Alice"), User(2, "Bob")), id1 = 1, id2 = 99))


  def forEx4(users: Seq[User], id1: Int, id2: Int): Option[String] =
    for {
      n <- users
      if n.find(id1) && n.find(id2)
    } yield s"${n.find(id1)}さんと${nn.find(id2)}さん"
