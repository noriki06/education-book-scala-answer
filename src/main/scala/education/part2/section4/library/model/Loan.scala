package education.part2.section4.library.model

import ixias.core.model.*

/**
 * 貸出エンティティ（前章で定義。DB を何も知らない純粋な case class）
 */
case class Loan(
  date:     Strign,                 // 日時
  status:   Loan.Status,            // 操作
  bookTitle: Option[Loan.Title] ,   // 対象の蔵書
  user:     String,       　　　　　// 利用者
  loanResult: Loan.Result 　　　　　// 貸出／返却APIの挙動
  updatedAt: LocalDateTime = Now,   // 更新日時
  createdAt: LocalDateTime = Now,   // 作成日時
) extends EntityModel[Loan,Title]

object Loan:
  object Title extends EntityId[Long]
  type Title         = Title.Repr

  enum Status(val code: Int) extends EnumStatus[Int]:
    case Rent   extends Status(1)
    case Return extends Status(2)

  enum Result(val code: Int) extends EnumStatus[Int]:
    case Success extends Result(1)
    case Failure extends Result(2)
