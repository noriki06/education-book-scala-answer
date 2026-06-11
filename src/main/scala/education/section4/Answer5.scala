package education.section4

object Answer5:
  case class Item(name: String, price: Int)   // ← object の中に定義する

  def caseClassQuestion2(a: Item, b: Item): Boolean =
    a == b


  def main(args: Array[String]): Unit =
    println(caseClassQuestion2(Item("apple", 120), Item("apple", 120)))
    println(caseClassQuestion2(Item("apple", 120), Item("apple", 150)))
