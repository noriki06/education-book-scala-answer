package education.section7

object AnswerEx3:

  case class Product(
    name: String,
    stock: Int
  )

  def main(args: Array[String]): Unit =
    println(forEx3(Seq(
      Product("ペン", 5),
      Product("ノート", 0),
      Product("消しゴム", 3)
    ) ) )
  def forEx3(products: Seq[Product]): Seq[String] =
    for {
      n <- products
      if n.stock >= 1
    } yield n.name
