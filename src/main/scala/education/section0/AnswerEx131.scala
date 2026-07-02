package education.section0

object AnswerEx131:

  case class User(name: String, age: Int)

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int): String = s"整数($value)"

  given Show[String] with
    def show(value: String): String = s"文字列(\"$value\")"

  given Show[User] with
    def show(u: User): String = s"${u.name}(${u.age}歳)"

  def typeclassQuestion3(user: User)(using s: Show[User]): String =
    s.show(user)


  def main(args: Array[String]): Unit =
    println(typeclassQuestion3(User("Alice", 20)))
