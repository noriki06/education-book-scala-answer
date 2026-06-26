package education.section11

object Answer4:

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int) = s"整数($value)"


  def typeclassQuestion1(value: Int)(using s: Show[Int]): String =
    s.show(value)

  def main(args: Array[String]): Unit =
    println(typeclassQuestion1(42))
