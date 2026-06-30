package education.section0

object AnswerEx86:

  def main(args: Array[String]): Unit =
    println(forEx1(Seq(2, 3)))

  def forEx1(xs: Seq[Int]): Seq[String] =
    xs.flatMap(a => xs.map(b => s"$a * $b = ${a * b}" ))
