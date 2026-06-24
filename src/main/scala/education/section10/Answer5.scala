package education.section10

object Answer5:

  abstract class Shape(val name: String):

    def area:     Double
    def describe: String = s"$name の面積は $area"

  class Rectangle(width: Double, height: Double) extends Shape("長方形"):
    def area = width * height

  def traitQuestion5(rect: Rectangle): String =
    rect.describe

  def main(args: Array[String]): Unit =
    println(traitQuestion5(Rectangle(3, 4)))
