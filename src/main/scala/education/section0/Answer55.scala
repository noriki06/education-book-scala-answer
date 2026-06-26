package education.section0

object Answer55:

  case class Student(name: String, score: Int)

  enum Grade:
    case S
    case A
    case B
    case C

  def main(args: Array[String]): Unit =
    println(ccEx5(Student("Alice", 80)))

  def ccEx5(student: Student): Grade =
    student match
      case Student(_, score) if score >= 90  => Grade.S
      case Student(_, score) if score >= 80  => Grade.A
      case Student(_, score) if score >= 60  => Grade.B
      case _  => Grade.C
