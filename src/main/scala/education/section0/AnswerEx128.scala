package education.section0

object AnswerEx128:

  case class TaxRate(value: Double)

  given TaxRate = TaxRate(0.10)

  def givenQuestion3(): Double =
    summon[TaxRate].value



  def main(args: Array[String]): Unit =
    println(givenQuestion3())
