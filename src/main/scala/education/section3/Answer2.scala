package education.section3

object Answer2:

  def seqQuestion2(numSeq: Seq[Int])=
    numSeq.filter(n => n % 3 == 0)

  def main(args: Array[String]): Unit =
    println(seqQuestion2(Seq(1, 3, 4, 6, 9)))
    println(seqQuestion2(Seq(1, 2)))
