package education.section2

object AnswerEx5:

  def optionEx5(s: String): String =
    s.toIntOption match
      case Some(value) => s"OK: $value"
      case None        => "NG"
      
  def main(args: Array[String]): Unit =
    println(optionEx5("123"))
    println(optionEx5("abc"))
