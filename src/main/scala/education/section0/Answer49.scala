package education.section0

object Answer49:

  case class Student(name: String, score: Int)
  object Student:
    val PassingScore = 60

  def main(args: Array[String]): Unit =
    println(companionQuestion1(Student("Alice", 80)))

  def companionQuestion1(student: Student): Boolean =
      if student.score >= Student.PassingScore then true
      else false
