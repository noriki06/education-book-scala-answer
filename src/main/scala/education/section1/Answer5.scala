package education.section1

object Answer5:
  def main(args: Array[String]): Unit =
    println(isAdult(20))
    println(isAdult(17))
    println(isAdult(18))

  def isAdult(age: Int): Boolean =
    age >= 18


