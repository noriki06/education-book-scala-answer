package education.section1

object Answer2:
  val Pi: Double = 3.14
  def main(args: Array[String]): Unit =
    println(circleArea(2.0))

  def circleArea(r: Double): Double =
    Pi * r * r

