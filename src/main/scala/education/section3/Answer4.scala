package education.section3

object Answer4:
  def seqQuestion4(strSeq: Seq[String]): Seq[String] =
    strSeq.collect { case s if s.length >= 2 => s + "x" }

 


  def main(args: Array[String]): Unit =
    println(seqQuestion4(Seq("a", "bb", "ccc")))
    println(Seq("bbx", "cccx"))
