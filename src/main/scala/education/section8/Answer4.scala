package education.section8

object Answer4:

  def eitherQuestion3(s: String): Either[String, Int] =
    s.toIntOption.toRight(s"数値に変換できません: $s")

  def eitherQuestion4(a: String, b: String): Either[String, Int] =
    for {
      x <- eitherQuestion3(a)
      y <- eitherQuestion3(b)
      c = x + y
    } yield c


  def main(args: Array[String]): Unit =
    println(eitherQuestion4(a = "10", b = "20"))
    println(eitherQuestion4(a = "10", b = "x"))
