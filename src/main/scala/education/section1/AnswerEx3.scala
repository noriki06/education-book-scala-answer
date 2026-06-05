package education.section1

object AnswerEx3:
  def grade(score: Int): String =
    if score >= 90 then "A"
    else if score >= 70 then "B"
    else if score >= 50 then "C"
    else "D"


  def main(args: Array[String]): Unit =
    println(grade(95))
    println(grade(72))
    println(grade(50))
    println(grade(30))
