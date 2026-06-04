package education.section2
object Answer5:
  def main(args: Array[String]): Unit =
    println(optionQuestion5(Some(Some(3))))
    println(optionQuestion5(Some(None)))
    println(optionQuestion5(None))

  def optionQuestion5(nested: Option[Option[Int]]): Option[Int] =
    nested.flatten

