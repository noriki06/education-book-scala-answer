package education.section11

object Answer6:

  case class User(name: String, age: Int)

  given Show[User] with
    def show(u: User) = s"${u.name}(${u.age}歳)"

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int) = s"整数($value)"

  given Show[String] with
    def show(value: String) = s"文字列(\"$value\")"


  def typeclassQuestion3(user: User)(using s: Show[User]): String =
    s.show(user)

  def main(args: Array[String]): Unit =
    println(typeclassQuestion3(User("Alice", 20)))
