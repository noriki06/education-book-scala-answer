package education.section0

object Answer7:

  def main(args: Array[String]): Unit =
    println(Rectangle(3, 4).area)
    println(Rectangle(3, 4).perimeter)
    println(Rectangle(3, 4).isSquare)
    println(Rectangle(5, 5).area)
    println(Rectangle(5, 5). perimeter)
    println(Rectangle(5, 5).isSquare)


  class Rectangle(width: Int, height: Int):
    def area: Int = width * height

    def perimeter: Int = 2 * (width + height)

    def isSquare: Boolean =
      if width == height then true
      else                    false
