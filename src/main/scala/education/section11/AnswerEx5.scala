package education.section11

object AnswerEx5:

  trait Combine[A]:
    def empty: A                    // 「何もない」状態（合計の 0、連結の "" にあたる）
    def combine(x: A, y: A): A

  trait Show[A]:
    def show(value: A): String

  given Show[Int] with
    def show(value: Int): String = value.toString

  given [A](using s: Show[A]): Show[Seq[A]] with
    def show(xs: Seq[A]): String = xs.map(s.show).mkString("[", ", ", "]")

  def showQuestion(xs: Seq[Int]): String =
    summon[Show[Seq[Int]]].show(xs)

  def main(args: Array[String]): Unit =
    println(showQuestion(Seq(1, 2, 3)))
