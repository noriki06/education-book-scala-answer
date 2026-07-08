package education.part2.section0

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer17:

  case class Point(
    x: Int,
    y: Int
  )

  given Ordering[Point] = OrderingSyntax.derived

  def main(args: Array[String]): Unit =
    val xs = List(Point(2, 1), Point(1, 1), Point(3, 0))

    println(xs.sorted)
