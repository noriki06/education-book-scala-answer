package education.section0

object Answer17:

  def main(args: Array[String]): Unit =
    println(optionQuestion7(Some(10)))

  def optionQuestion7(num: Option[Int]): String =
    num match
      case Some(num) => s"値は${num}"
      case None      => "なし"
