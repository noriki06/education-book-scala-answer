package education.section0

object AnswerEx119:
  def main(args: Array[String]): Unit =
    println(sealedQuestion1(Card("1234")))

  sealed trait PaymentMethod
  case class Card(number: String) extends PaymentMethod
  case class Cash(amount: Int)    extends PaymentMethod

  def sealedQuestion1(p: PaymentMethod): String =
    p match
      case Card(n) => s"カード支払い: ${n}"
      case Cash(a) => s"現金支払い: ${a} 円"
