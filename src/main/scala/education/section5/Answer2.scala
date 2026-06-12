package education.section5

object Answer2:
  def setQuestion2(a: Set[Int], b: Set[Int]): Set[Int] =
    a.intersect(b)

  def setQuestiondiff2(a: Set[Int], b: Set[Int]): Set[Int] =
    a.diff(b)

  def setQuestionplus2(a: Set[Int], b: Set[Int]): Set[Int] =
    a ++ b

  def main(args: Array[String]): Unit =
    println(setQuestion2(Set(1, 2, 3), Set(2, 3, 4)))
    println(setQuestiondiff2(Set(1, 2, 3), Set(2, 3, 4)))
    println(setQuestionplus2(Set(1, 2, 3), Set(2, 3, 4)))
