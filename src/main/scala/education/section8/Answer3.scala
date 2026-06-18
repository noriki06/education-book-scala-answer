package education.section8

object Answer3:


  def eitherQuestion3(s: String): Either[String, Int] =
    s.toIntOption.toRight(s"数値に変換できません: $s")


  def main(args: Array[String]): Unit =
    println(eitherQuestion3("42"))
    println(eitherQuestion3("abc"))
