package education.section0

object AnswerEx130:

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int): String = s"整数($value)"

  given Show[String] with
    def show(value: String): String = s"文字列(\"$value\")"

  def typeclassQuestion2[A](value: A)(using s: Show[A]): String =
    s.show(value)


  def main(args: Array[String]): Unit =
    println(typeclassQuestion2("hi"))
