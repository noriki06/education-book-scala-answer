package education.section0

object Answer12:

  def main(args: Array[String]): Unit =
    println(optionQuestion2(Some("")))

  def optionQuestion2(str: Option[String]): Boolean =
    str.isDefined
