package education.section0

object Answer67:

  def msEx1(day1: Set[String], day2: Set[String]): Set[String] =
    day1.intersect(day2)



  def main(args: Array[String]): Unit =
    println(msEx1(Set("Alice", "Bob", "Carol"), Set("Bob", "Carol", "Dave")))
