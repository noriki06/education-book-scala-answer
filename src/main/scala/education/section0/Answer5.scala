package education.section0

object Answer5:

  def main(args: Array[String]): Unit =
    println(isAdult(20))

  def isAdult(age: Int): Boolean =
    if age >= 20 then true
    else              false
