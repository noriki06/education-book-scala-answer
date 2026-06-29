package education.section12

object AnswerEx2:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*
  import cats.data.EitherT
  import cats.syntax.all.*

  case class Order(
    id: Int,
    customerId: Int,
    productId: Int,
    quantity: Int
  )

  case class Customer(
    id: Int,
    name: String
  )

  case class Product(
    id: Int,
    name: String,
    price: Int
  )

  enum ErrorType:
    case NoneOrder
    case NoneCustomer
    case NoneProduct

  case class Details(
    customerName: String,
    productName: String,
    amount: String,
    totalAmount: String
  )

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
    val result2: Either[ErrorType, Details] = Await.result(detailsByOrderId(100, orders, customers, products), Duration.Inf)
    println(result2)
    val result3: Either[ErrorType, Details] = Await.result(detailsByOrderId(101, orders, customers, products), Duration.Inf)
    println(result3)
    val result4: Either[ErrorType, Details] = Await.result(detailsByOrderId(999, orders, customers, products), Duration.Inf)
    println(result4)

  def orderToMap(orders: Map[Int, Order], id: Int): Future[Either[ErrorType, Order]] =
    Future {
      Thread.sleep(500)
      orders
        .get(id)
          .toRight(ErrorType.NoneOrder)
    }

  def customerToMap(customers: Map[Int, Customer], id: Int): Future[Either[ErrorType, Customer]] =
    Future {
      Thread.sleep(500)
      customers
        .get(id)
          .toRight(ErrorType.NoneCustomer)
    }

  def productToMap(products: Map[Int, Product], id: Int): Future[Either[ErrorType, Product]] =
    Future {
      Thread.sleep(500)
      products
        .get(id)
          .toRight(ErrorType.NoneProduct)
    }

  def detailsByOrderId(
    orderId: Int,
    orders: Map[Int, Order],
    customers: Map[Int, Customer],
    products: Map[Int, Product]
    ): Future[Either[ErrorType, Details]] =
    (for {
      order <- EitherT(orderToMap(orders, orderId))
      customer <- EitherT(customerToMap(customers, order.customerId))
      product <- EitherT(productToMap(products, order.productId))
    } yield Details(customer.name, product.name, s"数量${order.quantity}", s"合計${product.price * order.quantity}円")).value
