package education.section0

object AnswerEx84:

  case class Product(
    name: String,
    stock: Int
  )

  def main(args: Array[String]): Unit =
    println(forEx3(Seq(Product("ペン", 5), Product("ノート", 0), Product("消しゴム", 3))))

  def forEx3(products: Seq[Product]): Seq[String] =
    for {
      a <- products
      if a.stock >= 1
    } yield a.name
