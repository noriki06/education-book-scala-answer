package education.section1

object PreferVal1:
  def main(args: Array[String]): Unit =
    var price = 1000
    price = price + 500   // 送料を足す
    price = price - 200   // クーポンで値引き
    println(price)
