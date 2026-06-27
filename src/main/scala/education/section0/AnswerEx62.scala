package education.section0

object Answer62:

  def main(args: Array[String]): Unit =
    println(mapQuestion5(prices, "melon", 300))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion5(prices: Map[String, Int], name: String, price: Int): Map[String, Int] =
    prices.updated(name, price)
