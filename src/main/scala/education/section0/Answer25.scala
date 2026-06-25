package education.section0

object Answer25:

  def main(args: Array[String]): Unit =
    println(seqQuestion4(Seq("a", "bb", "ccc")))

  def seqQuestion4(strSeq: Seq[String]): Seq[String] =
    strSeq.collect{ case str if str.size >= 2 => s"${str}x" }
