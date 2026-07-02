package education.section0

object AnswerEx122:

  sealed trait MyOption[A]
  case class MySome[A](value: A) extends MyOption[A]   // 値がある
  case class MyNone[A]()         extends MyOption[A]   // 値がない

  def myOptionQuestion2[A](opt: MyOption[A], default: A): A =
    opt match
      case MySome(a) => a
      case MyNone() => default


  def main(args: Array[String]): Unit =
    println(myOptionQuestion2(MySome(42), 0))
