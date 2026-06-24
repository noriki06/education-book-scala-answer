package education.section10

object Answer7:

  case class Box[A](value: A)

  def genericQuestion2[A](xs: Seq[A]): A =
    xs.last

  def main(args: Array[String]): Unit =
    println(genericQuestion2(Seq(1, 2, 3)))
    println(genericQuestion2(Seq("a", "b", "c")))
