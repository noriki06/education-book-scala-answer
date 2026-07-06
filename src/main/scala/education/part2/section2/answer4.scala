package education.part2.section2

import ixias.core.model.*          // 状態の find / of を使う問題では ixias.core.model.syntax.* も

object Answer4:


  object Member:
    object Id extends EntityId[Long]
    type Id = Id.Repr

    enum Status(val code: Int) extends EnumStatus[Int]:
    case Active    extends Status(1) // 有効
    case Suspended extends Status(2) // 一時停止中
    case Withdrawn extends Status(3) // 退会

  def main(args: Array[String]): Unit =
    import ixias.core.model.syntax.*
    val id: Member.Id = Member.Id(1001L)

    println(Member.Status.of(2))
    println(Member.Status.find(99))
    println(Member.Status.Active.code)
