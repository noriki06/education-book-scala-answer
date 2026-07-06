package education.part2.section2

import ixias.core.model.*
import ixias.core.model.syntax.*

object AnswerOrdering4:

  case class Point(
    x: Int,
    y: Int
  )


  def main(args: Array[String]): Unit =
    println(List(Point(1, 2), Point(1, 1), Point(0, 3)).sorted)
