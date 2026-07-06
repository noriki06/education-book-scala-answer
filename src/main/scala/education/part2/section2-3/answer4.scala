package education.part2.section2-3

import ixias.core.model.*
import ixias.core.model.syntax.*

object Answer4:

  case class Point(
    x: Int,
    y: Int
  )

  val xs = List(Some(1), Some(2), Some(3), None)

  def main(args: Array[String]): Unit =
    println(List(Point(1, 2), Point(1, 1), Point(0, 3)).sorted)
