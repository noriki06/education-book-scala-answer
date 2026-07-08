package education.part2.section0

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer14:

  def main(args: Array[String]): Unit =
    val xs = List(Some(3), None, Some(1), Some(2))

    println(xs.sorted(using summon[Ordering[Int]].noneLastAsc))
