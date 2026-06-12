package education.section5

object Answer6:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion4(prices: Map[String, Int], name: String): Boolean =
    prices.contains(name)

  def main(args: Array[String]): Unit =
    println(mapQuestion4(prices, "kiwi"))
    println(mapQuestion4(prices, "melon"))
