package education.section5

object AnswerEx5:
  case class Student(name: String, team: String, score: Int)

  def msEx5(students: Seq[Student]): Map[String, Int] =
    students.groupMap(student => student.team)(t => t.score).view.mapValues(scores => scores.sum).toMap 

  def main(args: Array[String]): Unit =
    println(msEx5(Seq(Student("Alice", "A", 80), Student("Bob", "B", 95), Student("Carol", "A", 60))))
