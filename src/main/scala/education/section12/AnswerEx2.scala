package education.section12

object AnswerEx1:

  case class Order(
    id: Order.Id,
    customerId: Customer.Id,
    productId: Product.id,
    quantity: Int
  )

  object Order:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Customer(
    id: Customer.Id,
    name: String
  )

  object Customer:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Product(
    id: Product.Id,
    name: String,
    price: Int
  )

  object Product:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  enum ErrorType:
    case NoneOrder
    case NoneCustomer
    case NoneProduct

  case class OrderDetails(
    customerName: String,
    productName: String,
    amount: Int,
    totalAmount: Int
  )

  def main(args: Array[String]): Unit =
    val orders =
      Map(
        Order(100 -> (1, 10, 2)),
        Order(101 -> ( 2, 99, 1))
      )

    val customers =
      Map(
        Customer(1 -> "田中"),
        Customer(2 -> "佐藤")
      )

    val products =
      Map(
        Product(10 -> ("ノート", 300))
      )

  def orderToMap(orders: Order): Future[Either[OrderDetails, Order]] =
    orders
      .get(id)
        .toRight()


  def customerToMap(customers: Customer): Future[Either[OrderDetails, Customer]] =



  def productToMap(products: Product): Future[Either[OrderDetails, Product]] =
