package education.section1

object AnswerEx1:

  class Rectangle(width: Int, height: Int):
    def area: Int = width * height

    def perimeter: Int = 2 * (width + height)

    def isSquare: Boolean = width == height
  
  val r1 = Rectangle(3, 4)
  val r2 = Rectangle(5, 5) 

  def main(args: Array[String]): Unit =
    println(s"area=${r1.area}, perimeter=${r1.perimeter}, isSquare=${r1.isSquare}")
    println(s"area=${r2.area}, perimeter=${r2.perimeter}, isSquare=${r2.isSquare}")

