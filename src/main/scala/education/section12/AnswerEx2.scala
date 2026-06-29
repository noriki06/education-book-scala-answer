package education.section12

object AnswerEx2:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*
  import cats.data.EitherT
  import cats.syntax.all.*
  import cats.data.Validated
  import cats.data.ValidatedNel  /**
   *注文のケースクラス
   */
  case class Order(
    id:         Int, // 注文id
    customerId: Int, // 顧客id　
    productId:  Int, // 商品id
    quantity:   Int  // 数量
  )

  /**
   * 顧客のケースクラス
   */
  case class Customer(
    id:   Int,   // 顧客id
    name: String // 顧客名
  )

  /**
   *商品のケースクラス
   */
  case class Product(
    id:    Int,    // 商品id
    name:  String, // 商品名
    price: Int     // 単価
  )

  /**
   * 注文詳細のケースクラス
   */
  case class Details(
    customerName: String, // 顧客の氏名
    productName:  String, // 商品名
    amount:       String, // 数量
    totalAmount:  String  // 合計金額
  )

  /**
   * エラーのタイプ
   */
  enum ErrorType:
    case NoneOrder(id: Int)    // 注文が見つからない
    case NoneCustomer(id: Int) // 顧客が見つからない
    case NoneProduct(id: Int)  // 商品が見つからない


  def main(args: Array[String]): Unit =
    val orders: Map[Int, Order] =
      Map(
        (100, Order(100, 1, 10, 2)),
        (101, Order(101, 2, 99, 1))
      )

    val customers: Map[Int, Customer] =
      Map(
        (1, Customer(1, "田中")),
        (2, Customer(2, "佐藤"))
      )

    val products: Map[Int, Product] =
      Map(
        (10, Product(10, "ノート", 300))
      )
    val result2: Either[ErrorType, Details] = Await.result(detailsByOrderId(100, 3, orders, customers, products), Duration.Inf)
    println(result2)
    val result3: Either[ErrorType, Details] = Await.result(detailsByOrderId(101, 2, orders, customers, products), Duration.Inf)
    println(result3)
    val result4: Either[ErrorType, Details] = Await.result(detailsByOrderId(999, 1, orders, customers, products), Duration.Inf)
    println(result4)

  /**
   * orders を order_id で引くメソッド
   */
  def orderToMap(orders: Map[Int, Order], id: Int): Future[Either[ErrorType, Order]] =
    Future {
      Thread.sleep(500)
      orders
        .get(id)
          .toRight(ErrorType.NoneOrder(id))
    }

  /**
   * customers を customer_id で引くメソッド
   */
  def customerToMap(customers: Map[Int, Customer], id: Int): Future[Either[ErrorType, Customer]] =
    Future {
      Thread.sleep(500)
      customers
        .get(id)
          .toRight(ErrorType.NoneCustomer(id))
    }

  /**
   * products を product_id で引くメソッド
   */
  def productToMap(products: Map[Int, Product], id: Int): Future[Either[ErrorType, Product]] =
    Future {
      Thread.sleep(500)
      products
        .get(id)
          .toRight(ErrorType.NoneProduct(id))
    }

  /**
   * 顧客・商品・数量から 注文詳細を組み立てるメソッド
   */
  def detailsByOrderId(
    orderId: Int,
    quantity: Int,
    orders: Map[Int, Order],
    customers: Map[Int, Customer],
    products: Map[Int, Product]
    ): Future[Either[ErrorType, Details]] =
    (for {
      a        <- EitherT.fromEither[Future](validate(orderId, quantity).toEither.leftMap(error => error.head))
      order    <- EitherT(orderToMap(orders, orderId))
      customer <- EitherT(customerToMap(customers, order.customerId))
      product  <- EitherT(productToMap(products, order.productId))
    } yield Details(
      customer.name,
      product.name,
      s"数量${order.quantity}",
      s"合計${product.price * order.quantity}円"
      )
    ).value

  def validateOrderId(orderId: Int): ValidatedNel[ErrorType, Int] =
    if orderId > 0 then orderId.validNel
    else ErrorType.NoneOrder(orderId).invalidNel

  def validateQuantity(quantity: Int): ValidatedNel[ErrorType, Int] =
    if quantity > 0 then quantity.validNel
    else ErrorType.NoneProduct(quantity).invalidNel

  def validate(orderId: Int, quantity: Int): ValidatedNel[ErrorType, (Int, Int)] =
    (validateOrderId(orderId), validateQuantity(quantity))
      .mapN((id, qty) => (id, qty))
