package education.section0

object AnswerEx97:
  case class Product(
    id: Int,
    name: String,
    price: Int,
    stock: Int
  )

  enum Error:
    case NonNumber(num: String)
    case NonProduct(id: Int)
    case NonStock(amount: Int)

  val products: Seq[Product] =
    Seq(
      Product(1, "ノート", 200, 10),
      Product(2, "ボールペン", 100, 0),
      Product(3, "消しゴム", 80, 5),
      Product(4, "はさみ", 350, 3)
    )

  val productMap = products.map(product => product.id -> product).toMap

  def toInt(number: String): Either[Error ,Int] =
    number.toIntOption.filter(num => num >= 1).toRight(Error.NonNumber(number))

  def findProduct(productMap: Map[Int, Product], id: Int): Either[Error, Product] =
    productMap.get(id).toRight(Error.NonProduct(id))

  def checkOrder(product: Product, amount: Int): Either[Error, Unit] =
    product.stock match
      case st if st < amount => Left(Error.NonStock(amount))
      case _ => Right(())


  def totalOrder(id: Int, amount: String): Either[Error, Int] =
    for {
      a <- toInt(amount) // 数量:Int
      b <- findProduct(productMap, id) // 商品:Product
      c <- checkOrder(b, a) // Unit
    } yield b.price * a

  def matchError(total: Either[Error, Int]): String =
    total match
      case Left(Error.NonNumber(num))  => s"${num}は不正な数値です。"
      case Left(Error.NonProduct(id)) => s"ID: ${id} はありません"
      case Left(Error.NonStock(amount))   => s"在庫が足りません（在庫 ${amount} 個）"
      case Right(s)         => s"合計${s}円です"

  def total(orders: Seq[(Int, String)]): (Seq[Error], Int) =
    val(failures, successes) =
      orders.partitionMap {
        case (id, amount) => totalOrder(id, amount)
      }
    (failures, successes.sum)



  def main(args: Array[String]): Unit =
    println(total(Seq((1, "3"), (1, "abc"), (99, "1"), (2, "1"))))
