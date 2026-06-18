package education.section8

object AnswerEx1:

  /**
   *ユーザーのケースクラス
   */
  case class User(
    name: String,
    age:  Int
  )

    /**
     *名前の検証
     */
    def validateName(name: String): Either[String, String] =
      name match
        case name if name == "" => Left("名前が空です")
        case name               => Right(name)

    /**
     *年齢の検証
     */
    def validateAge(age: Int): Either[String, Int] =
      age match
        case age if age < 0   => Left("年齢が不正です")
        case age              => Right(age)
    /**
     *ユーザー情報の検証
     */
    def validateUser(name: String, age: Int): Either[String, User] =
      for{
        a <- validateName(name)
        b <- validateAge(age)
      }yield User(a, b)

    def main(args: Array[String]): Unit =
      println(validateUser("Alice", 20))
      println(validateUser("", 20))
      println(validateUser("Bob", -1))
