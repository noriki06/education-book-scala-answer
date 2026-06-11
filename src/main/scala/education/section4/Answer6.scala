package education.section4

object Answer6:
  case class Item(name: String, price: Int)   // ← object の中に定義する

  def caseClassQuestion3(item: Item): Item =
    item.copy(price = item.price * 2)

  def main(args: Array[String]): Unit =
    println(caseClassQuestion3(Item("apple", 120)))
    println(caseClassQuestion3(Item("apple", 120)))

