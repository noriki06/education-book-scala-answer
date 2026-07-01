package education.section0

object AnswerEx118:

  def genericQuestion2[A](xs: Seq[A]): A =
    xs.last
  def main(args: Array[String]): Unit =
    println(genericQuestion2(Seq(1, 2, 3)))
