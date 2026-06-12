package education.section5

object Answer7:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion5(prices: Map[String, Int], name: String, price: Int): Map[String, Int] =
    prices.updated(name, price)

  def main(args: Array[String]): Unit =
    println(mapQuestion5(prices, "melon", 300))
    println(mapQuestion5(prices, "apple", 100))
    println(prices)
