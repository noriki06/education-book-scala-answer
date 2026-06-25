package education.section0

object Answer33:

  def main(args: Array[String]): Unit =
    println(seqEx3(Seq(Seq(1, 2), Seq(3, 9), Seq(4, 5))))

  def seqEx3(numSeqSeq: Seq[Seq[Int]]): Seq[Seq[Int]] =
    numSeqSeq.filter(inner => inner.exists(num => num % 3 == 0))
