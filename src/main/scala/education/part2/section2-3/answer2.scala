package education.part2.section2-3

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer2:

  val xs = List(Some(3), Some(2), Some(1), None)

  def main(args: Array[String]): Unit =
    println(xs.sorted(using summon[Ordering[Int]].noneLastDesc))
