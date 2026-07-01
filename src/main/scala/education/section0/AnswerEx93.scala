package education.section0

object AnswerEx93:

  case class User(
    name: String,
    age: Int
  )

  def main(args: Array[String]): Unit =
    println(validateUser("Alice", 20))

  def namecheck(name: String): Either[String, String] =
    name match
      case name if name == "" => Left("名前が空です")
      case name => Right(name)

  def agecheck(age: Int): Either[String, Int] =
    age match
      case age if age < 0 => Left("年齢が不正です")
      case age => Right(age)

  def validateUser(name: String, age: Int): Either[String, User] =
    for {
      a <- namecheck(name)
      b <- agecheck(age)
    } yield User(name, age)
