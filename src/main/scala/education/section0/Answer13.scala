package education.section0

object Answer13:

  def main(args: Array[String]): Unit =
    println(optionQuestion3(Some(5)))

  def optionQuestion3(num: Option[Int]) =
    num.map(num => num + 3)
