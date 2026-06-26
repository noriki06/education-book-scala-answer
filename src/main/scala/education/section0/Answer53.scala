package education.section0

object Answer53:

  case class Student(name: String, score: Int)

  def main(args: Array[String]): Unit =
    println(ccEx3(Seq(Student("Alice", 80), Student("Bob", 95), Student("Carol", 50))))

  def ccEx3(students: Seq[Student]): Seq[String] =
    students.filter(s => s.score >= 80).map(s => s.name)
