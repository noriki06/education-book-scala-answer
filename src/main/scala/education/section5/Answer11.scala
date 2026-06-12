package education.section5

object Answer11:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion9(prices: Map[String, Int]): Map[String, Int] =
    prices.view.mapValues(prices => prices + 30).toMap

  def main(args: Array[String]): Unit =
    println(mapQuestion9(prices))
