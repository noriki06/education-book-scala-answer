package education.section5

object Answer5:

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion3(prices: Map[String, Int], name: String): Int =
    prices.getOrElse(name, 0)


  def main(args: Array[String]): Unit =
    println(mapQuestion3(prices, "banana"))
    println(mapQuestion3(prices, "melon"))
