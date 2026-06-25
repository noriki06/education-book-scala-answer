package education.section0

object Answer8:

  def main(args: Array[String]): Unit =
    println(priceWithTax(1000, 0.1))

  def priceWithTax(price: Int, taxRate: Double): Int =
    (price * (1 + taxRate)).toInt
