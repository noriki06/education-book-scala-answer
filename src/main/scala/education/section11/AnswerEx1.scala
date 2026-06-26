package education.section11

object AnswerEx1:

  trait Combine[A]:
    def empty: A                    // 「何もない」状態（合計の 0、連結の "" にあたる）
    def combine(x: A, y: A): A

  given Combine[Int] with
    def empty = 0
    def combine(x: Int, y: Int) = x + y

  given Combine[String] with
    def empty = ""
    def combine(x: String, y: String) = x + y


  def combineQuestion1(): Seq[String] =
    Seq(
      summon[Combine[Int]].combine(3, 4).toString,
      summon[Combine[String]].combine("ab", "cd")
    )

  def main(args: Array[String]): Unit =
    println(combineQuestion1())
