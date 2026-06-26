package education.section11

object AnswerEx2:

  trait Combine[A]:
    def empty: A                    // 「何もない」状態（合計の 0、連結の "" にあたる）
    def combine(x: A, y: A): A

  given Combine[Int] with
    def empty = 0
    def combine(x: Int, y: Int) = x + y

  given Combine[String] with
    def empty = ""
    def combine(x: String, y: String) = x + y


  def combineAll[A: Combine](xs: Seq[A]): A =
    val c = summon[Combine[A]]
    xs.foldLeft(c.empty)(c.combine)

  def main(args: Array[String]): Unit =
    println(combineAll(Seq(1, 2, 3, 4)))
    println(combineAll(Seq("a", "b", "c")))
