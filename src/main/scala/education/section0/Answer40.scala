package education.section0
class Student(val name: String, val score: Int)

object Answer40:

  def main(args: Array[String]): Unit =
    val students: Seq[Student] = Seq(
      Student("Alice", 80),
      Student("Bob",   95),
      Student("Carol", 70)
    )
    println(averageScore(students))

  def ranking(students: Seq[Student]): Seq[String] =
    students.sortBy(s => -s.score).map(s => s.name)

  def averageScore(students: Seq[Student]): Double =
    if students.isEmpty then 0.0
    else                     (students.map(s => s.score).sum.toDouble) / students.size.toDouble
