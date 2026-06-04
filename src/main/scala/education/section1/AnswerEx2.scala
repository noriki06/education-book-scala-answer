package education.section1

object AnswerEx2:
  def priceWithTax(price: Int, taxRate: Double): Int =
    (price * (1 + taxRate)).toInt


  def main(args: Array[String]): Unit =
    println(priceWithTax(1000, 0.1))
    println(priceWithTax(980, 0.08))

