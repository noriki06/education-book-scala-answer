package education.section2
object Answer6:
  def main(args: Array[String]): Unit =
    println(optionQuestion6(Some(8)))
    println(optionQuestion6(Some(7)))
    println(optionQuestion6(None))

  def optionQuestion6(num: Option[Int]): Option[Int] =
    num.flatMap(i => if i % 2 == 0 then Some(i / 2) else None)

