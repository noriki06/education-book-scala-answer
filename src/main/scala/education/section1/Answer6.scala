package education.section1

object Answer6:
  def main(args: Array[String]): Unit =
    println(r.area)

  class Rectangle(width: Int, height: Int):
    def area: Int = width * height
  
  val r = Rectangle(3, 4)
