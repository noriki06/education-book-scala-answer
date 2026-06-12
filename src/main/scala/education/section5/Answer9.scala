package education.section5

object Answer9:
  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150) 

  def mapQuestion7(prices: Map[String, Int]): Int =
    prices.values.sum

  def main(args: Array[String]): Unit =
    println(mapQuestion7(prices))
