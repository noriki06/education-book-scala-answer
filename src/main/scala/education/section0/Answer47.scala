package education.section0

object Answer47:

  case class Rectangle(width: Int, height: Int)

  def main(args: Array[String]): Unit =
    println(caseClassQuestion4(Rectangle(3, 4)))

  def caseClassQuestion4(rect: Rectangle): Int =
    rect.width * rect.height
