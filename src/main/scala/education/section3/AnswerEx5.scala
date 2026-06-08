package education.section3

object AnswerEx5:
  def seqEx5(people: Seq[(String, Int)]): Seq[String] =
    people.sortBy(t => (-t._2, t._1)).map(t => t._1)


  def main(args: Array[String]): Unit =
    println(seqEx5(Seq(("Alice", 80), ("Bob", 95), ("Carol", 80))))
