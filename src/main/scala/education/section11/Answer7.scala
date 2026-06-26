package education.section11

object Answer7:

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int) = s"整数($value)"

  given Show[String] with
    def show(value: String) = s"文字列(\"$value\")"


  def typeclassQuestion4[A: Show](value: A): String =
    summon[Show[A]].show(value)

  def main(args: Array[String]): Unit =
    println(typeclassQuestion4(42))
    println(typeclassQuestion4("hi"))
