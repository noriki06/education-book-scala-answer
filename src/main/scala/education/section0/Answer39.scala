package education.section0

object Answer39:

  def main(args: Array[String]): Unit =
    println(totalNumber(hand))

  class Card(val suit: String, val number: Int)

  val hand: Seq[Card] = Seq(
    Card("♠", 10),
    Card("♥", 3),
    Card("♦", 7)
  )

    def sortedLabels(cards: Seq[Card]): Seq[String] =
      cards.sortBy(c => c.number).map(card => s"${card.suit}${card.number}")

    def totalNumber(cards: Seq[Card]): Int =
      cards.map(card => card.number).sum
