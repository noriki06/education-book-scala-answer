package education.section0

object Answer57:

  def main(args: Array[String]): Unit =
    println(setQuestion2(Set(1, 2, 3), Set(2, 3, 4)))

  def setQuestion2(a: Set[Int], b: Set[Int]): Set[Int] =
    a.intersect(b)
