package education.section8

object AnswerEx2:

  /**
   *ユーザーのケースクラス
   */
  case class User(
    name: String,
    age:  Int
  )

  enum ValidationError:
    case EmptyName  // 名前が空
    case InvalidAge // 年齢が不正

   /**
     *ユーザー情報の検証
     */
  def validateUser(name: String, age: Int): Either[ValidationError, User] =
    (name, age) match
      case (name, age) if name == "" => Left(ValidationError.EmptyName)
      case (name, age) if age < 0    => Left(ValidationError.InvalidAge)
      case (name, age)               => Right(name, age)

  def describe(result: Either[ValidationError, User]): String =
    result(name, age) match
      case Right(user)                            => s"登録成功: ${user.name} (${user.age})"
      case Left(ValidationError.EmptyName)        => "名前を入力してください"
      case Left(ValidationError.InvalidAge(v))    => s"年齢が不正です: $v"

  def main(args: Array[String]): Unit =
    println(describe(validateUser("Alice", 20)))
    println(describe(validateUser("", 20)))
