package education.section0

object Answer15:

  def main(args: Array[String]): Unit =
    println(optionQuestion5(Some(Some(3))))

  def optionQuestion5(nested: Option[Option[Int]]): Option[Int] =
    nested.flatten
