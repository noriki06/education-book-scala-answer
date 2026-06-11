package education.section4

object AnswerEx3:
  case class Student(name: String, score: Int)  

  def ccEx3(students: Seq[Student]): Seq[String] =
    students.collect { case Student(name, score) if score >= 80 => name }
    //students.filter(_.score >= 80).map(_.name)  filterとmapでもかけることを確認

  def main(args: Array[String]): Unit =
    println(ccEx3(Seq(Student("Alice", 80), Student("Bob", 95), Student("Carol", 50))))

