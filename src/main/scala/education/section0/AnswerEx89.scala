package education.section0

object AnswerEx89:

  def main(args: Array[String]): Unit =
    println(eitherQuestion3("abs"))

  def eitherQuestion3(s: String): Either[String, Int] =
    s.toIntOption.toRight("数値に変換できません: " + s)
