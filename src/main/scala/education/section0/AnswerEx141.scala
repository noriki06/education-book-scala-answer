package education.section0

object AnswerEx141:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*
  import cats.data.EitherT
  import cats.syntax.all.*

  case class Order(
    id: Int,
    customerId: Int,
    productId: Int,
    amount: Int
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

  case class Detail(
    customerName: String,
    productName: String,
    amount: Int,
    total: Int
  )

  enum Error:
    case Nonorder
    case Noncustomer
    case Nonproduct

  def main(args: Array[String]): Unit =

    val orders: Map[Int, Order] =
      Map(
        (100, Order(100, 1, 10, 2)),
        (101, Order(101, 2, 99, 1))
      )

    val customers: Map[Int, Customer] =
      Map(
        (1, Customer(a, "田中")),
        (2, Customer(b, ""佐藤))
      )

    val products: Map[Int, Product] =
      Map(
        (10, Product(10, "ノート", 300))
      )

    val result2: Either[NonEmptyList[Error], Details] = Await.result(detail(100, orders, customers, products), Duration.Inf)
    println(result2)
    val result3: Either[NonEmptyList[Error], Details] = Await.result(detail(101, orders, customers, products), Duration.Inf)
    println(result3)
    val result4: Either[NonEmptyList[Error], Details] = Await.result(detail(999, orders, customers, products), Duration.Inf)
    println(result4)





  def checkOrder(id: Int): Future[Either[Error, Order]] =
    Future { Thread.sleep(500)
      orders.get(id).toRight(Error.Nonorder)
    }

  def checkCustomer(id: Int): Future[Either[Error, Customer]] =
    Future { Thread.sleep(500)
      customers.get(id).toRight(Error.Noncustomer)
    }

  def checkProduct(id: Int): Future[Either[Error, Product]] =
    Future { Thread.sleep(500)
      products.get(id).toRight(Error.Monproduct)
    }

  def detail(orderId: Int, orders: Order, customers: Cuistomer, products: Product): Future[Either[Error, Detail]] =
    (for {
      order <- EitherT(checkOrder(orderId))
      customer <- EitherT(checkCustomer(order.cutomerId))
      product <- EitherT(checkProduct(order.productId))
    } yield Detail(customer.name, product,name, order.amount, product.price * order.amount)).Value
