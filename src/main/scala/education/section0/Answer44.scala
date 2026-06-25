package education.section0

object Answer44:
  case class Item(name: String, price: Int)

  def main(args: Array[String]): Unit =
    println(caseClassQuestion1())

  def caseClassQuestion1(): Item =
    Item("apple", 120)
