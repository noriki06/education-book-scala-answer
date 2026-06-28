package education.section0

object Answer71:

  case class Student(name: String, team: String, score: Int)

  def main(args: Array[String]): Unit =
    println(msEx5(Seq(Student("Alice", "A", 80), Student("Bob", "B", 95), Student("Carol", "A", 60))))

  def msEx5(students: Seq[Student]): Map[String, Int] =
    students
      .groupMap(student => student.team)(team => team.score)
      .view
      .mapValues(score => score.sum)
      .toMap
