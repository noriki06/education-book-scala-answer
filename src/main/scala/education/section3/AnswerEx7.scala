package education.section3

object AnswerEx7:
  def seqEx7(strSeq: Seq[String]): Seq[Int] =
    strSeq.flatMap(s => s.toIntOption)
    //option化してnoneは捨てて、整数変換できたものだけ残る＝＞それをflat

  def main(args: Array[String]): Unit =
    println(seqEx7(Seq("12", "x", "30")))
