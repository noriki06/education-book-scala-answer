package education.part2.section2-3

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer1:

  val xs = List(Some(1), Some(2), Some(3), None)

  def main(args: Array[String]): Unit =
    println(xs.sorted(using summon[Ordering[Int]].noneLastAsc))
    println(xs.sorted(using summon[Ordering[Int]].noneFirstAsc))
