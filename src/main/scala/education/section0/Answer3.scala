package education.section0

object Answer3:

  def evenOdd(a: Int): String =
    if a % 2 == 0 then "even"
    else               "odd"



  def main(args: Array[String]): Unit =
    println(evenOdd(4))
