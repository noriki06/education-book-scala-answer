package education.section0

object AnswerEx138:

  case class Member(name: String, score: Int)

  given Ordering[Member] =
    Ordering.by[Member, Int](_.score).reverse


  def orderingQuestion(members: Seq[Member]): Seq[Member] =
    members.sorted


  def main(args: Array[String]): Unit =
    println(orderingQuestion(Seq(Member("A", 50), Member("B", 90), Member("C", 70))))
