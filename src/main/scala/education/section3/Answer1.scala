package education.section3

object Answer1:
  def seqQuestion1(numSeq: Seq[Int]) =
    numSeq.map(i => i * i)

  def main(args: Array[String]): Unit =
    println(seqQuestion1(Seq(1, 2, 3)))
    println(seqQuestion1(Seq()))
