package education.section0

object AnswerEx127:

  case class TaxRate(value: Double)

  given TaxRate = TaxRate(0.10)

  def givenQuestion1(price: Int)(using rate: TaxRate): Int =
    (price * (1 + rate.value)).toInt



  def main(args: Array[String]): Unit =
    given TaxRate = TaxRate(0.08)

    println(givenQuestion1(1000))
