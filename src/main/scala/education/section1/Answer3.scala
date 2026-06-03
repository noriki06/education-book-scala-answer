package education.section1

object Answer3:
  def main(args: Array[String]): Unit =
    println(evenOdd(4))
    println(evenOdd(7))

  def evenOdd(n: Int): String =
    if n % 2 == 0 then "even" else "odd"

