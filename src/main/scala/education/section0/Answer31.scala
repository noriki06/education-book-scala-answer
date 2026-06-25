package education.section0

object Answer31:

  def main(args: Array[String]): Unit =
    println(seqEx1(Seq(1, 2, 3, 4, 5, 6)))

  def seqEx1(numSeq: Seq[Int]): Seq[Int] =
    numSeq.filter(num => num % 2 == 0).map(num => num * num)
