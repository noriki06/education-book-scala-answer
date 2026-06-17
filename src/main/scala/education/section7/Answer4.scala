package education.section7

object Answer4:

  def main(args: Array[String]): Unit =
    println(forQuestion4(xs = Seq(1, 2), ys = Seq(10, 100)))

  def forQuestion4(xs: Seq[Int], ys: Seq[Int]): Seq[Int] =
    for {
      a <- xs
      b <- ys
      doubled = a * b
    } yield doubled
