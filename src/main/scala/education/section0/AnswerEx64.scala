package education.section0

object Answer64:

  def main(args: Array[String]): Unit =
    println(mapQuestion7(prices))

  val prices = Map("apple" -> 120, "banana" -> 200, "kiwi" -> 150)

  def mapQuestion7(prices: Map[String, Int]): Int =
    prices.values.sum
