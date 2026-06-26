package education.section0

object Answer50:

  case class Student(id: Student.Id, name: String)
  object Student:                          // ← クラスと同じ名前の object ＝ コンパニオンオブジェクト
    opaque type Id = Long                  // Student 専用の ID 型
    object Id:
      def apply(value: Long): Id = value

  def main(args: Array[String]): Unit =
    println(Student(Student.Id(1), "Alice"))
