package education.section0

object Answer65:

  def main(args: Array[String]): Unit =
    println(mapQuestion8(prices))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion8(prices: Map[String, Int]): Map[String, Int] =
    prices.filter { (name, price) => price >= 150 }
