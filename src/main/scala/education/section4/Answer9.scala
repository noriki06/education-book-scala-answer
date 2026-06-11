package education.section4

object Answer9:
  case class Student(name: String, score: Int)
  object Student:
    val PassingScore = 60

  def companionQuestion1(student: Student): Boolean =
    student.score >= Student.PassingScore

  def main(args: Array[String]): Unit =
    println(companionQuestion1(Student("Alice", 80)))
    println(companionQuestion1(Student("Carol", 50)))
