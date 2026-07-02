package education.section0

object AnswerEx121:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def parseInt(s: String): MyOption[Int] =
    s.toIntOption match
      case Some(s) => MySome(s)
      case None() => MyNone[Int]()


  def myOptionQuestion4(s: String): Int =
    parseInt(s)



  def main(args: Array[String]): Unit =
    println(myOptionQuestion4("21"))
