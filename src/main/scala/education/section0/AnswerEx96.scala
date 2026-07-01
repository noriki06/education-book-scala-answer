package education.section0

object AnswerEx96:

  case class Item(
    id: Int,
    name: String,
    price: Int
  )

  def totalPrice(items: Seq[Item], id1: Int, id2: Int): Either[String, Int] =
    for {
      a <- items.find(item => item.id == id1).toRight(s"id:${id1} が見つかりません")
      b <- items.find(item => item.id == id2).toRight(s"id:${id2} が見つかりません")
    } yield a.price + b.price



  def main(args: Array[String]): Unit =
    println(totalPrice(Seq(Item(1, "ペン", 100), Item(2, "ノート", 200)), 1, 2))
