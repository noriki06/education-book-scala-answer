package education.section8

object AnswerEx4:

  /**
   *アイテムのケースクラス
   */
  case class Item(
    id:    Int,    // アイテムID
    name:  String, // アイテム名
    price: Int     // 値段
  )

  def totalPrice(items: Seq[Item], id1: Int, id2: Int): Either[String, Int] =
    for {
      a <- items.find(_.id == id1).toRight(s"id:${id1} が見つかりません")
      b <- items.find(_.id == id2).toRight(s"id:${id2} が見つかりません")
    } yield a.price + b.price

  def main(args: Array[String]): Unit =
    println(totalPrice(items = Seq(Item(1, "ペン", 100), Item(2, "ノート", 200)), id1 = 1, id2 = 2))
    println(totalPrice(items = Seq(Item(1, "ペン", 100), Item(2, "ノート", 200)), id1 = 1, id2 = 99))
