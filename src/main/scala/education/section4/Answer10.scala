package education.section4

object Answer10:
  case class Student(id: Student.Id, name: String)
  object Student:
    opaque type Id = Long                  // Student 専用の ID 型
    object Id:
      def apply(value: Long): Id = value   // ← Id を作る「窓口」（前節の宿題の回収）

    enum Gender:                           // Student 専用の選択肢
      case Male, Female, Unselected

  def main(args: Array[String]): Unit =
    println(Student(Student.Id(1), "Alice"))

