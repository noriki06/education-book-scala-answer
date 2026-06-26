package education.section0

object Answer46:
  case class Item(name: String, price: Int)

  def main(args: Array[String]): Unit =
    println(caseClassQuestion3(Item("apple", 120)))

  def caseClassQuestion3(item: Item): Item =
    item.copy(price = item.price * 2)
