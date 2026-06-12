package education.section5

object Answer10:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion8(prices: Map[String, Int]): Map[String, Int] =
    prices.filter { case (name, price) => price >= 150 }

  def main(args: Array[String]): Unit =
    println(mapQuestion8(prices))
