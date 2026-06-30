package education.section0

object AnswerEx90:

  def main(args: Array[String]): Unit =
    println(eitherQuestion4("10", "20"))

  def eitherQuestion3(s: String): Either[String, Int] =
    s.toIntOption.toRight(s"数値に変換できません: $s")

  def eitherQuestion4(a: String, b: String): Either[String, Int] =
    for {
      x <- eitherQuestion3(a)
      y <- eitherQuestion3(b)
      c = x + y
    } yield c
