package education.section5

object Answer8:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion6(prices: Map[String, Int], name: String): Map[String, Int] =
    prices.removed(name)

  def main(args: Array[String]): Unit =
    println(mapQuestion6(prices, "banana"))
    println(mapQuestion6(prices, "melon"))
