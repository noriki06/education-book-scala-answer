package education.section4

object Answer7:
  case class Rectangle(width: Int, height: Int)   // ← object の中に定義する

  def caseClassQuestion4(rect: Rectangle): Int =
    rect.width * rect.height

  def main(args: Array[String]): Unit =
    println(caseClassQuestion4(Rectangle(3, 4)))
