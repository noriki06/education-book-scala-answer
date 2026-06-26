package education.section0

case class Item(name: String, price: Int)

object Answer45:



  def main(args: Array[String]): Unit =
    println(caseClassQuestion2(Item("apple", 120), Item("apple", 120)))

  def caseClassQuestion2(a: Item, b: Item): Boolean =
    a == b
