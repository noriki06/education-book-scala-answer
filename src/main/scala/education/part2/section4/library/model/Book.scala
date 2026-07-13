package education.part2.section4.library

import ixias.core.model.*

/**
 * 本エンティティ
 **/
case class Book(
  id:       Option[Book.Id],
  title:    String,             // タイトル
  category: Book.Category,      // カテゴリー
  loanFlag: Book.Status         // 貸出フラグ（初期）
)

object Book:
  object Id extends EntityId[Long]
  type Id         = Id.Repr

  enum Category(val code: Int) extends EnumStatus[Int]:
    case Novel     extends Category(1)
    case Manga     extends Category(2)
    case Technical extends Category(3)
    case Magazine  extends Category(4)

  enum Status(val code: Int) extends EnumStatus[Int]:
    case Possible    extends Status(1)
    case Inpossible  extends Status(2)
