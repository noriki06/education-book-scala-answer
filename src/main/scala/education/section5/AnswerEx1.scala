package education.section5

object AnswerEx1:

  def msEx1(day1: Set[String], day2: Set[String]): Set[String] =
    day1.intersect(day2)

  def msdiffEx1(day1: Set[String], day2: Set[String]): Set[String] =
    day1.diff(day2)

  def msplusEx1(day1: Set[String], day2: Set[String]): Set[String] =
    day1 ++ day2

  def main(args: Array[String]): Unit =
    println(msEx1(Set("Alice", "Bob", "Carol"), Set("Bob", "Carol", "Dave")))
    println(msdiffEx1(Set("Alice", "Bob", "Carol"), Set("Bob", "Carol", "Dave")))
    println(msplusEx1(Set("Alice", "Bob", "Carol"), Set("Bob", "Carol", "Dave")))
