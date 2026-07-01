package education.section0

object AnswerEx94:

  case class User(
    name: String,
    age:  Int
  )

  enum ValidationError:
    case NonName
    case NonAge

  def main(args: Array[String]): Unit =
    println(describe(validateUser("Alice", 20)))

  def validateUser(name: String, age: Int): Either[ValidationError, User] =
    (name, age) match
      case (name, age) if name == "" => Left(ValidationError.NonName)
      case (name, age) if age < 0 => Left(ValidationError.NonAge)
      case _ => Right(User(name, age))

  def describe(result: Either[ValidationError, User]): String =
    result match
      case Left(ValidationError.NonName) => "名前が空です"
      case Left(ValidationError.NonAge) => "年齢が不正です"
      case Right(user) => s"登録成功: ${user.name} (${user.age})"
