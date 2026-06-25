package education.section0

object Answer14:

  def main(args: Array[String]): Unit =
    println(optionQuestion4(Some("scala")))

  def optionQuestion4(str: Option[String]): Option[String] =
    str.map(_ + "!")
