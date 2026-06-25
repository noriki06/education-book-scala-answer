package education.section0

object Answer9:

  def main(args: Array[String]): Unit =
    println(grade(95))

  def grade(score: Int): String =
    if      score >= 90 then "A"
    else if score >= 70 then "B"
    else if score >= 50 then "C"
    else                     "D"
