package education.section2
object Answer4:
  def main(args: Array[String]): Unit =
    println(optionQuestion4(Some("scala")))
    println(optionQuestion4(None))

  def optionQuestion4(str: Option[String]): Option[String] =
    str.map(_.toUpperCase + "!")

