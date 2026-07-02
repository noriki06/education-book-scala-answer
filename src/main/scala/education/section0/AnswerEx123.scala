package education.section0

object AnswerEx123:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def myOptionQuestion3[A, B](opt: MyOption[A], f: A => B): MyOption[B] =
    opt match
      case MySome(v) => MySome(f(v))
      case MyNone() => MyNone()

  def main(args: Array[String]): Unit =
    println(myOptionQuestion3(MySome(10), (x: Int) => x * 2))
