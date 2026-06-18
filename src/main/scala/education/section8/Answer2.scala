package education.section8

object Answer2:

  def eitherQuestion2(e: Either[String, Int]): Int =
    e.getOrElse(0)

  def main(args: Array[String]): Unit =
    println(eitherQuestion2(Right(7)))
    println(eitherQuestion2(Left("エラー")))
