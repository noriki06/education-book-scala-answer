package education.section0

object Answer63:

  def main(args: Array[String]): Unit =
    println(mapQuestion6(prices, "banana"))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion6(prices: Map[String, Int], name: String): Map[String, Int] =
    prices.removed(name)
