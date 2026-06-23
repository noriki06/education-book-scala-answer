package education.section8

object AnswerCheckpointShop:

  /**
   * 商品のケースクラス
   */
  case class Product(
    id:    Int,    //商品ID
    name:  String, //商品名
    price: Int,    //価格
    stock: Int     //在庫数
  )

  enum TypeOrderFailure:
    case IncorrectQuantity(s: String)                      // 数量が正しくない
    case NotFoundProduct  (id: Int)                        // 商品が見つからない
    case NotEnoughStock   (orderQuantity: Int, stock: Int) // 在庫が足りない

  val products: Seq[Product] = // 商品マスタ
    Seq(
      Product(1, "ノート", 200, 10),
      Product(2, "ボールペン", 100, 0),
      Product(3, "消しゴム", 80, 5),
      Product(4, "はさみ", 350, 3)
    )

  /**
   * 商品ID で引ける Map
   */
  val productMap = products.map(product => product.id -> product).toMap

  /**
   * 問 2: 数量を数値に変換する
   */
  def inputValueToQuantity(inputValue: String): Either[TypeOrderFailure, Int] =
    inputValue
      .toIntOption
      .filter(i => i > 0)
      .toRight(TypeOrderFailure.IncorrectQuantity(inputValue))

  /**
   * 問 3: 商品を探す
   */
  def findProductById(productMap: Map[Int, Product], id: Int): Either[TypeOrderFailure, Product] =
    productMap
      .get(id)
      .toRight(TypeOrderFailure.NotFoundProduct(id))


  /**
   * 問 4: 在庫を確認し、注文全体を for で合成する
   */
  def checkStock(product: Product, orderQuantity: Int): Either[TypeOrderFailure, Unit] =
    product.stock match
    case s if s < orderQuantity => Left(TypeOrderFailure.NotEnoughStock(orderQuantity, product.stock))
    case _                      => Right(())

  /**
   * 注文の合計金額を出す
   */
  def totalPriceForOrder(productId: Int, inputValue: String): Either[TypeOrderFailure, Int] =
    for{
      quantity <- inputValueToQuantity(inputValue)
      product  <- findProductById(productMap, productId)
      _        <- checkStock(product, quantity)
    }yield product.price * quantity

  /**
   * 結果を読みやすいメッセージにする
   */
  def toMessage(result: Either[TypeOrderFailure, Int]): String =
    result match
      case Right(money)
        => s"合計 $money 円"
      case Left(TypeOrderFailure.IncorrectQuantity(inputValue))
        => s"${inputValue}は不正な入力値です"
      case Left(TypeOrderFailure.NotFoundProduct(id))
        => s"ID:${id}の商品がありません"
      case Left(TypeOrderFailure.NotEnoughStock(orderQuantity, stock))
        => s"在庫が足りません（在庫 $stock／利用 $orderQuantity）"

  /**
   * 複数注文をまとめて処理する
   */
  def summarizeOrders(requests: Seq[(Int, String)]): (Seq[TypeOrderFailure], Int) =
    val (failures, successes) =
      requests
        .partitionMap { case (id, inputValue) => totalPriceForOrder(id, inputValue) }

    (failures, successes.sum)

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
    println(totalPriceForOrder(1, "abc"))
    println(totalPriceForOrder(99, "1"))
    println(totalPriceForOrder(2, "1"))
    // 問５
    //
    println(toMessage(totalPriceForOrder(1, "3")))
    println(toMessage(totalPriceForOrder(1, "abc")))
    println(toMessage(totalPriceForOrder(99, "1")))
    println(toMessage(totalPriceForOrder(2, "1")))

    println(summarizeOrders(Seq((1, "3"), (1, "abc"), (99, "1"), (2, "1"))))
