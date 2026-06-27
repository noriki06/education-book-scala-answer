package education.section0

object Answer66:

  def main(args: Array[String]): Unit =
    println(mapQuestion9(prices))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion9(prices: Map[String, Int]): Map[String, Int] =
    prices.view.mapValues(p => p + 30).toMap
