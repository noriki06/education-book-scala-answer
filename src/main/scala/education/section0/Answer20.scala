package education.section0

object Answer20:

  def main(args: Array[String]): Unit =
    println(divChain(20, 2, 5))

  def divide(a: Int, b: Int): Option[Int] =
    if b == 0 then None
    else Some(a / b)


  def divChain(a: Int, b: Int, c: Int): Option[Int] =
    divide(a, b).flatMap(ab => divide(ab, c))
