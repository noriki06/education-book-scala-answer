package education.section0

object AnswerEx126:

  case class TaxRate(value: Double)

  given TaxRate = TaxRate(0.10)

  def givenQuestion1(price: Int)(using rate: TaxRate): Int =
    (price * (1 + rate.value)).toInt



  def main(args: Array[String]): Unit =
    println(givenQuestion1(1000))
