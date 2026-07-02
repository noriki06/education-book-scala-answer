package education.section0

object AnswerEx121:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def myOptionQuestion1(): Seq[String] =
    Seq(MySome(42).toString)



  def main(args: Array[String]): Unit =
    println(myOptionQuestion1())
