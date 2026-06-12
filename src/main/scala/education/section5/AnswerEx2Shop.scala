package education.section5

object AnswerEx2Shop:

  case class Customer(
    id:   Customer.Id,//顧客ID
    name: String      //顧客名前
  )

  object Customer:                       
    opaque type Id = Long                    
    object Id:
      def apply(value: Long): Id = value

  case class Order(
    id:         Order.Id,   //注文ID
    customerId: Customer.Id,//顧客ID
    amount:     Int,        //
    status:     Order.Status//ステータス３種類を定義（enum）
  )

  object Order:
    opaque type Id = Long 
    object Id:
      def apply(value: Long): Id = value

    enum Status:                           
      case Pending, Shipped, Cancelled

  def main(args: Array[String]): Unit =
  
    val customers: Seq[Customer] =
      Seq(
        Customer(Customer.Id(1), "Alice"), 
        Customer(Customer.Id(2), "Bob"), 
        Customer(Customer.Id(3), "Carol"), 
        Customer(Customer.Id(4), "Dave")
      )

    val orders: Seq[Order] =
      Seq(
        Order(Order.Id(101), Customer.Id(1) , 3200, Order.Status.Shipped), 
        Order(Order.Id(102), Customer.Id(2), 1500, Order.Status.Pending), 
        Order(Order.Id(103), Customer.Id(1), 2800, Order.Status.Cancelled), 
        Order(Order.Id(104), Customer.Id(3), 5000, Order.Status.Shipped), 
        Order(Order.Id(105), Customer.Id(1), 1200, Order.Status.Pending),
        Order(Order.Id(106), Customer.Id(3), 700, Order.Status.Cancelled)
      )

    //val byId = customersById(customers)
    //println(customersById(customers))
    //println(findCustomerName(byId, Customer.Id(3)))
    //println(findCustomerName(byId, Customer.Id(99)))
    println(totalByCustomer(orders))


  def totalByCustomer(orders: Seq[Order]): Map[Customer.Id, Int] =
    val amountTotal =
      orders
      .filter(order => order.status != Order.Status.Cancelled)
      .groupBy(order => order.customerId)
      .view
      .mapValues(money => money.amount.sum)
      .toMap
    
    amountTotal
  //def customersById(customers: Seq[Customer]): Map[Customer.Id, Customer] =
    //val byId = customers.map(customer => customer.id -> customer)
    //byId.toMap

  //def findCustomerName(byId: Map[Customer.Id, Customer], id: Customer.Id): String =
    //byId.get(id).map(customer => customer.name).getOrElse("不明")

