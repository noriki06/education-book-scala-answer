package education.section8

object AnswerEx3:

  import scala.util.{Try}

  def parse(s: String): Either[String, Int] =
    Try(s.toInt)
      .toEither
      .left.map(e => s"数値に変換できません: " + s)

  def sumThree(a: String, b: String, c: String): Either[String, Int] =
    for{
      x <- parse(a)
      y <- parse(b)
      z <- parse(c)
    }yield x + y + z

  def main(args: Array[String]): Unit =
    println(sumThree("1", "2", "3"))
    println(sumThree("1", "x", "3"))
