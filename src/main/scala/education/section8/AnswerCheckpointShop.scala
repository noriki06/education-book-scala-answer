package education.section8

object AnswerCheckpointShop:

  /**
   *商品のケースクラス
   */
  case class Product(
    id:    Int,    //商品ID
    name:  String, //商品名
    price: Int,    //価格
    stock: Int     //在庫数
  )

  enum TypeOrderFailure:
    case IncorrectQuantity(s: String) // 数量が正しくない
    case NotFoundProduct   // 商品が見つからない
    case NotEnoughStock(orderQuantity: Int, stock: Int)    //在庫が足りない

  val products: Seq[Product] = // 商品マスタ
    Seq(
      Product(1, "ノート", 200, 10),
      Product(2, "ボールペン", 100, 0),
      Product(3, "消しゴム", 80, 5),
      Product(4, "はさみ", 350, 3)
    )

  /**
   *商品ID で引ける Map
   */
  val productMap = products.map(product => product.id -> product).toMap

  /**
   *問 2: 数量を数値に変換する
   */
  def inputValueToQuantity(inputValue: String): Either[TypeOrderFailure, Int] =
    inputValue
      .toIntOption
      .filter(i => i > 0)
      .toRight(TypeOrderFailure.IncorrectQuantity(inputValue))

  /**
   *問 3: 商品を探す
   */
  def findProductById(productMap: Map[Int, Product], id: Int): Either[TypeOrderFailure, Product] =
    productMap
      .get(id)
      .toRight(TypeOrderFailure.NotFoundProduct)


  /**
   *問 4: 在庫を確認し、注文全体を for で合成する
   */
  def checkStock(product: Product, orderQuantity: Int): Either[TypeOrderFailure, Unit]
    product.stock match
    case s if s < orderQuantity => Left(TypeOrderFailure.NotEnoughStock(orderQuantity, product.stock))
    case _                      => Right()

  def totalPriceForOrder(productId: Int, inputValue: String): Either[TypeOrderFailure, Int]
    for{
      x <- inputValueToQuantity(inputValue)
      y <- findProductById(productMap, productId)
      z <- checkStock(y, x)
    }yield y.price * x

  def main(args: Array[String]): Unit =
    // 問2
    println(inputValueToQuantity("3"))
    println(inputValueToQuantity("abc"))
    println(inputValueToQuantity("0"))
    // 問3
    println(findProductById(productMap, 1))
    println(findProductById(productMap, 99))
    // 問４
    println(totalPriceForOrder(1, "3"))
