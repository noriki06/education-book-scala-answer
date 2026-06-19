package education.section8

object AnswerCheckpointShop:

  /**会議のリンク
   *商品のケースクラス
   */
  case class Product(
    id:    Int,    //商品ID
    name:  String, //商品名
    price: Int,    //価格
    stock: Int     //在庫数
  )

  enum TypeOrderFailure:
    case IncorrectQuantity // 数量が正しくない
    case NotFoundProduct   // 商品が見つからない
    case NotEnoughStock(s: String)    //在庫が足りない

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
  def productsById(products: Seq[Product]): Map[Int, Product] =
    products
      .map(product => product.id -> product)
      .toMap

  /**
   *問 2: 数量を数値に変換する
   */
  def inputValueToQuantity(inputValue: String): Either[TypeOrderFailure, Int] =
    inputValue
      .toIntOption
      .filter(i => i > 0)
      .toRight(TypeOrderFailure.IncorrectQuantity)


  def main(args: Array[String]): Unit =
    // 問１
    println(inputValueToQuantity("3"))
    println(inputValueToQuantity("abc"))
    println(inputValueToQuantity("0"))
    //問２
