package education.section0

object Answer41:

  def main(args: Array[String]): Unit =
    println(tupleQuestion1("Alice", 80))

  def tupleQuestion1(name: String, score: Int): (String, Int) =
    (name -> score)
