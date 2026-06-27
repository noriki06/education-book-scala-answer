package education.section0

object Answer59:

  def main(args: Array[String]): Unit =
    println(mapQuestion2(prices, "apple"))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion2(prices: Map[String, Int], name: String): Option[Int] =
    prices.get(name)
