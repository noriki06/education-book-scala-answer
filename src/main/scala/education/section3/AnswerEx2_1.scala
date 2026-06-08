package education.section3

class Card(val suit: String, val number: Int)
object AnswerEx2_1:
// 種類（suit: ♠ ♥ ♦ ♣ などのマーク）と数字（number）を持つ 1 枚のカード
  def sortedLabels(cards: Seq[Card]): Seq[String]  =
    cards.sortBy(_.number).map(t => s"${t.suit}${t.number}")
      //数値で昇順でならぶ＞suit, numberをs"${suit}${number}"
      //seq[String
  def totalNumber(cards: Seq[Card]): Int = 
    cards.map(_.number).sum

  def main(args: Array[String]): Unit =
    val hand: Seq[Card] = Seq(
      Card("♠", 10),
      Card("♥", 3),
      Card("♦", 7)
    )
      //seq[Card]
    println(sortedLabels(hand))
    println(totalNumber(hand))
