package education.section5

object Answer4:

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion2(prices: Map[String, Int], name: String): Option[Int] =
    prices.get(name)

    
  def main(args: Array[String]): Unit =
    println(mapQuestion2(prices, "apple"))
    println(mapQuestion2(prices, "melon"))
