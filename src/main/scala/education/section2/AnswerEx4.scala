package education.section2

object AnswerEx4:

  def optionEx4(nested: Option[Option[Int]]): Int =
    nested match
      case Some(Some(x)) => x
      case Some(None) => 0
      case None => 0


  def main(args: Array[String]): Unit =
    println(optionEx4(Some(Some(7))))
    println(optionEx4(Some(None)))
    println(optionEx4(None))
