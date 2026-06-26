package education.section0

object Answer54:

  case class Student(name: String, score: Int)

  def main(args: Array[String]): Unit =
    println(ccEx4(Seq(("Alice", 80), ("Bob", 95))))

  def ccEx4(pairs: Seq[(String, Int)]): Seq[Student] =
    pairs.map { case (name, score) => Student(name, score) }
