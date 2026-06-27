package education.section0

object Answer60:

  def main(args: Array[String]): Unit =
    println(mapQuestion3(prices, "banana"))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion3(prices: Map[String, Int], name: String): Int =
    prices.getOrElse(name, 0)
