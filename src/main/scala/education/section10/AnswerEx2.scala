package education.section10

object AnswerEx2:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def myOptionQuestion2[A](opt: MyOption[A], default: A): A =
    opt match
      case MySome(v) => v
      case MyNone()  => default

  def main(args: Array[String]): Unit =
    println(myOptionQuestion2(MySome(42), 0))
    println(myOptionQuestion2(MyNone[Int](), 0))
