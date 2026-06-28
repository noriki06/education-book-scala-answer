package education.section0

object Answer73:

  case class Customer(
    id: Customer.Id,
    name: String
  )

  object Customer:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Order(
    id: Order.Id,
    customerId: Customer.Id,
    amount: Int,
    status: Order.Status
  )

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
      Customer(Customer.Id(1), "Alice"),
      Customer(Customer.Id(2), "Bob"),
      Customer(Customer.Id(3), "Carol"),
      Customer(Customer.Id(4), "Dave")
    )

  val orders: Seq[Order] =
    Seq(
      Order(Order.Id(101), Customer.Id(1), 3200, Order.Status.Shipped),
      Order(Order.Id(102), Customer.Id(2), 1500, Order.Status.Pending),
      Order(Order.Id(103), Customer.Id(1), 2800, Order.Status.Cancelled),
      Order(Order.Id(104), Customer.Id(3), 5000, Order.Status.Shipped),
      Order(Order.Id(105), Customer.Id(1), 1200, Order.Status.Pending),
      Order(Order.Id(106), Customer.Id(3), 700, Order.Status.Cancelled)
    )

  def main(args: Array[String]): Unit =
    val byId = customersById(customers)
    val name = findCustomerName(byId, Customer.Id(3)).getOrElse("不明")
    println(customers)
    println(orders.head)
    println(customersById(customers))
    println(name)

  def customersById(customers: Seq[Customer]): Map[Customer.Id, Customer] =
    customers.map(customer => customer.id -> customer).toMap

  def findCustomerName(byId: Map[Customer.Id, Customer], id: Customer.Id): Option[String] =
    byId.get(id).map(customer => customer.name)
