package education.section8

object AnswerEx1:

  /**
   *ユーザーのケースクラス
   */
  case class User(
    name: String,
    age:  Int
  )

  def validateName(name: String): Either[String, User] =
    name match
      case name if name == "" => Left("名前が空です")
      case                    => Right(name)

  def validateAge(age: String): Either[String, User] =
    age match
      case age if age < 0 => Left("年齢が不正です")
      case                  => Right(age)

  def validateUser(name: String, age: Int): Either[String, User]=
    for{
      a <- validateName(name)
      b <- validateAge(age)
    }yield User(a, b)

  def main(args: Array[String]): Unit =
    println(validateUser("Alice", 20))
    println(validateUser("", 20))
    println(validateUser("Bob", -1))
