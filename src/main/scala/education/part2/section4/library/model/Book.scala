package education.part2.section4.library

import ixias.core.model.*

/**
 * 本エンティティ
 **/
case class Book(
  id:        Option[Book.Id],
  title:     String,                 // タイトル
  category:  Book.Category,          // カテゴリー
  state:     Book.State,                 // 貸出フラグ（初期）
  updatedAt: LocalDateTime = Now,   // 更新日時
  createdAt: LocalDateTime = Now   // 作成日時
)extends EntityModel[Book.Id]

object Book:
  object Id extends EntityId[Long]
  type Id         = Id.Repr

  enum Category(val code: Int) extends EnumStatus[Int]:
    case Novel     extends Category(1)
    case Manga     extends Category(2)
    case Technical extends Category(3)
    case Magazine  extends Category(4)

  enum State(val code: Int) extends EnumStatus[Int]:
    case Available extends State(1)
    case OnLoan    extends State(2)
