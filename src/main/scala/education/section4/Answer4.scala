package education.section4

object Answer4:
  case class Item(name: String, price: Int)   // ← object の中に定義する

  def caseClassQuestion1(): Item =
    Item("apple", 120)

  def main(args: Array[String]): Unit =
    println(println(caseClassQuestion1()))
