package education.section10

object Answer6:

  case class Box[A](value: A)

  def genericQuestion1(): Seq[String] =
    Seq(Box(42).toString, Box("hello").toString)

  def main(args: Array[String]): Unit =
    println(genericQuestion1())
