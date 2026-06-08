package education.section3

object Answer6:
  def seqQuestion6(numSeq: Seq[Int]): Int =
    numSeq.headOption.getOrElse(-1)
    //先頭の要素をOption,数値かnone


  def main(args: Array[String]): Unit =
    println(seqQuestion6(Seq(10, 20, 30)))
    println(seqQuestion6(Seq()))
  
