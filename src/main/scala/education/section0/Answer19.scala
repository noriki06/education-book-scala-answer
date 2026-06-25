package education.section0

object Answer19:

  def main(args: Array[String]): Unit =
    println(divide(10, 2))

  def divide(a: Int, b: Int): Option[Int] =

    if b == 0 then None
    else           Some(a / b)
