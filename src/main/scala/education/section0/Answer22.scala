package education.section0

object Answer22:

  def main(args: Array[String]): Unit =
    println(optionEx5("123"))

  def optionEx5(s: String): String =
    s.toIntOption.map(num => s"OK: ${num}").getOrElse("NG")
