package education.section0

object Answer21:

  def main(args: Array[String]): Unit =
    println(optionEx4(Some(Some(7))))

  def optionEx4(nested: Option[Option[Int]]): Int =
    nested match
      case Some(Some(x)) => x
      case Some(None)    => 0
      case None          => 0
