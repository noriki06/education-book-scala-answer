package education.section0

object Answer6:

  def main(args: Array[String]): Unit =
    println(Rectangle(3, 4).area)

  class Rectangle(width: Int, height: Int):
    def area: Int =
      width * height
