package education.section11

object Answer5:

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int) = s"整数($value)"

  given Show[String] with
    def show(value: String) = s"文字列(\"$value\")"


  def typeclassQuestion2[A](value: A)(using s: Show[A]): String =
    s.show(value)

  def main(args: Array[String]): Unit =
    println(typeclassQuestion2(42))
    println(typeclassQuestion2("hi"))
