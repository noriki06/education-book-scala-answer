package education.part2.section2

import ixias.core.model.*
import ixias.core.model.syntax.*

object AnswerOrdering1:

  val xs = List(Some(3), None, Some(1), Some(2))

  def main(args: Array[String]): Unit =
    println(xs.sorted(using summon[Ordering[Int]].noneLastAsc))
    println(xs.sorted(using summon[Ordering[Int]].noneFirstAsc))
