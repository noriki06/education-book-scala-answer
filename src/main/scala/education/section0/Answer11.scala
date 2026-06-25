package education.section0

object Answer11:

  def main(args: Array[String]): Unit =
    println(optionQuestion1(Some(5)))

  def optionQuestion1(num: Option[Int]): Int =
    num.getOrElse(0)
