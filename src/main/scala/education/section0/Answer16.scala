package education.section0

object Answer16:

  def main(args: Array[String]): Unit =
    println(optionQuestion6(Some(8)))

  def optionQuestion6(num: Option[Int]): Option[Int] =
    num
      .flatMap
        (num => if num % 2 == 0 then Some(num / 2)
                else                 None
      )
