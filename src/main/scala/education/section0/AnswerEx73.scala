package education.section0

object Answer73:

  case class Customer:
    id: Customer.Id,
    name: String

  object Customer:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Order:
    id: Order.Id,
    customerId: Customer.Id,
    amount: Int,
    status: Order.Status

  object Order:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  enum Status:
    case Pending
    case Shipped
    case Cancelled

  val customers: Seq[Customer] =
    Seq(
      Customer(1, "Alice"),
      Customer(2, "Bob"),
      Customer(3, "Carol"),
      Customer(4, "Dave")
    )

  val orders: Seq[Order] =
    Seq(
      Order(101, 1, 3200, Status.Shipped),
      Order(102, 2, 1500, Status.Pending),
      Order(103, 1, 2800, Status.Cancelled),
      Order(104, 3, 5000, Status.Shipped),
      Order(105, 1, 1200, Status.Pending),
      Order(106, 3, 700, Status.Cancelled)
    )

  def main(args: Array[String]): Unit =
    println(customers)
    println(orders.head)
