package education.section5

object AnswerEx2Shop:

/**
 * カスタマー情報、オーダー情報を管理するケースクラスです。
 */

  case class Customer( // 顧客情報のケースクラス
    id:   Customer.Id, // 顧客ID
    name: String       // 顧客名前
  )

  object Customer:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

  case class Order(          // 注文情報のケースクラス
    id:         Order.Id,    // 注文ID
    customerId: Customer.Id, // 顧客ID
    amount:     Int,         // 金額
    status:     Order.Status // ステータス３種類を定義（enum）
  )

  object Order:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

    enum Status:
      case Pending,  // 受付中
           Shipped,  // 発送済み
           Cancelled // キャンセル

/**
 *データ情報：カスタマー情報、オーダー情報
 *
 */
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
        Order(Order.Id(106), Customer.Id(3), 700,  Order.Status.Cancelled)
    )

  /*
   *動作確認用のメインメソッド
   */
  def main(args: Array[String]): Unit =
    // 問１の出力
    println(customers)                               // val customers(顧客情報)の内容が正しいかを出力
    println(orders.head)                             // val orders(注文情報)の内容の確認、先頭の部分のみ出力
    // 問２の出力
    val byId = customersById(customers)              // 顧客idで顧客情報を取得できるようにした
    println(customersById(customers))                // 顧客idと顧客情報の関係を出力
    println(findCustomerName(byId, Customer.Id(3)))  // 顧客id3の時の出力
    println(findCustomerName(byId, Customer.Id(99))) // 顧客id99の時の出力
    // 問３の出力
    println(totalByCustomer(orders))                 // 顧客idごとの購入金額を出力
    // 問４の出力
    println(neverOrdered(customers, orders))        // 一度も注文していない顧客名を出力


  /**
   * 問２：顧客の Seq を、顧客 ID → 顧客 の Map に変換して返します。
   */
  def customersById(customers: Seq[Customer]): Map[Customer.Id, Customer] =
      customers                                   // 顧客情報
        .map(customer => customer.id -> customer) // 顧客idがキー、顧客情報がバリューのseqに変換
        .toMap                                    // マップに変換

  /**
   * customersByIdででた顧客idの名前を返します。見つからなければ "不明" を返します。
   */
  def findCustomerName(byId: Map[Customer.Id, Customer], id: Customer.Id): String =
    byId
      .get(id)                            // 顧客idを取り出す
      .map(customerId => customerId.name) // 顧客idから顧客名を取り出す
      .getOrElse("不明")                  // 顧客名があれば出力なければ不明と出力

/**
 * 問３：「顧客ごとに、いくら買ってくれているか」を集計
 */
  def totalByCustomer(orders: Seq[Order]): Map[Customer.Id, Int] =
    val orderedCustomerId =                                      // オーダーした顧客id
      orders                                                     // 全ての注文情報
        .filter(order => order.status != Order.Status.Cancelled) // オーダーステイタスがキャンセル以外のもを抽出
        .groupBy(order => order.customerId)                      // 顧客idでまとめる

    orderedCustomerId                           // オーダーした顧客id
      .view.mapValues(orderTotal => orderTotal
      .map(order => order.amount)               // 金額を取り出す
      .sum)                                     // 合計する
      .toMap                                    // mapに変換

/**
 *問４：一度も注文していない顧客を探す
 */
  def neverOrdered(customers: Seq[Customer], orders: Seq[Order]): Set[String] =
    val orderyes =                      // 注文したことある顧客id
      orders                            // オーダー履歴
        .map(order => order.customerId) // オーダー履歴からカスタマーIDを取り出す(Seq(id))
        .toSet                          // setに変換し重複をなしに(Set(id))

    val customerAll =                 // 顧客全員id
      customers                       // 顧客情報
        .map(customer => customer.id) // 顧客情報からカスタマーIDを取り出す(Seq(id))
        .toSet                        // setに変換し重複をなしに(Set(id))

    val orderno =       // 注文したことない顧客id
      customerAll       // 顧客全員id
        .diff(orderyes) // 顧客全員id - 注文したことある顧客id

    customers                                            // 顧客情報
      .filter(customer => orderno.contains(customer.id)) // 顧客情報から注文したことない顧客を取り出す
      .map(customer => customer.name)                    // 注文したことない顧客の名前を取り出す(seq(name))
      .toSet                                             // setに変換
