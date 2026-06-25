package education.section0

object Answer18:

  def main(args: Array[String]): Unit =
    println(optionEx1(Some(5)))

  def optionEx1(num: Option[Int]): Int =
    num.map(num => num * 2).getOrElse(-1)
