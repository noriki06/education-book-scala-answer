package education.section1

object Answer2:
  val PI: Double = 3.14
  def main(args: Array[String]): Unit =
    println(circleArea(2.0))

  def circleArea(r: Double): Double =
    PI * r * r


