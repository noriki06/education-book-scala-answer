package education.section2
object Answer7:
  def main(args: Array[String]): Unit =
    println(optionQuestion7(Some(10)))
    println(optionQuestion7(None))

  def optionQuestion7(num: Option[Int]): String =
    num match
      case Some(value) => s"値は${value}"
      case None        => "なし"

