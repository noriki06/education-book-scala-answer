package education.section5

object AnswerEx2Shop

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
  enum Status:                           
    case Pending, Shipped, Cancelled

def main(args: Array[String]): Unit =
  
  val customers: Seq[Customer] =
    Seq(
      (1, Alice), 
      (2, Bob), 
      (3, Carol), 
      (4, Dave)
    )

  val orders: Seq[Order] =
    Seq(
      (101, 1 , 3200, Shipped), 
      (102, 2, 1500, Pending), 
      (103, 1, 2800, Cancelled), 
      (104, 3, 5000, Shipped), 
      (105, 1, 1200, Pending),
      (106, 3, 700, Cancelled)
    )

  println(println(customers))
  println(println(orders.head))
