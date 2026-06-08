package education.section3

object Answer3:
  def seqQuestion3(numSeq: Seq[Int]): Option[Int] =
    numSeq.find(num => num % 2 == 0)



  def main(args: Array[String]): Unit =
    println(seqQuestion3(Seq(1, 3, 4, 6)))
    println(seqQuestion3(Seq(1, 3, 5)))
  
