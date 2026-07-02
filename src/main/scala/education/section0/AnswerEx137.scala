package education.section0

object AnswerEx137:

  case class Money(yen: Int)

  given Combine[Money] with
    def empty = Money(0)
    def combine(x: Money, y: Money) = Money(x.yen + y.yen)

  trait Combine[A]:
    def empty: A                    // 「何もない」状態（合計の 0、連結の "" にあたる）
    def combine(x: A, y: A): A      // 2 つを 1 つにまとめる

  given Combine[Int] with
    def empty = 0
    def combine(x: Int, y: Int) = x + y

  given Combine[String] with
    def empty = ""
    def combine(x: String, y: String) = x + y

  def combineQuestion3(items: Seq[Money]): Money =
    val c = summon[Combine[Money]]
    items.foldLeft(c.empty)(c.combine)








  def main(args: Array[String]): Unit =
    println(combineQuestion3(Seq(Money(100), Money(250), Money(50))))
