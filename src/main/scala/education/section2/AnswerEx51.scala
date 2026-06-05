package education.section2

object AnswerEx51:

  def optionEx5(s: String): String =
    s.toIntOption.map(i => "OK: " + i).getOrElse("NG")
  //s>some or none>some(ok: +1) >ok: +1 or NG
      
  def main(args: Array[String]): Unit =
    println(optionEx5("123"))
    println(optionEx5("abc"))
