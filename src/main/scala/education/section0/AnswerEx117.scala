package education.section0

object AnswerEx117:

  case class Box[A](value: A)

  def genericQuestion1(): Seq[String] =
    Seq(Box(42).toString, Box("hello").toString)

  def main(args: Array[String]): Unit =
    println(genericQuestion1())
