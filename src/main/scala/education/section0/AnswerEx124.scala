package education.section0

object AnswerEx124:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def parseInt(s: String): MyOption[Int] =
    s.toIntOption match
      case Some(s) => MySome(s)
      case None => MyNone[Int]()

  def myOptionQuestion2[A](opt: MyOption[A], default: A): A =
    opt match
      case MySome(a) => a
      case MyNone() => default

  def myOptionQuestion3[A, B](opt: MyOption[A], f: A => B): MyOption[B] =
    opt match
      case MySome(v) => MySome(f(v))
      case MyNone() => MyNone()

  def myOptionQuestion4(s: String): Int =
    myOptionQuestion2((myOptionQuestion3(parseInt(s), (x: Int) => x * 2)), 0)



  def main(args: Array[String]): Unit =
    println(myOptionQuestion4("21"))
