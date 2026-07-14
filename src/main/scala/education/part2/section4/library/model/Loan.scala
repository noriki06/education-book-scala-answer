package education.part2.section4.library.model

import ixias.core.model.*

/**
 * 貸出エンティティ（前章で定義。DB を何も知らない純粋な case class）
 */
case class Loan(
  id:       Option[Loan.Id],
  status:   Loan.Status,            // 操作
  user:      String,                 // 利用者
  loanResult: Loan.Result,           // 貸出／返却APIの挙動
  bookTitle: Title,
  loanedAt: LocalDateTime = Now,
  updatedAt: LocalDateTime = Now,   // 更新日時
  createdAt: LocalDateTime = Now,   // 作成日時
) extends EntityModel[Loan.Id]

object Loan:
  object Id extends EntityId[Long]
  type Id         = Id.Repr
  type WithNoId   = Entity.WithNoId[Id, Loan]
  type EmbeddedId = Entity.EmbeddedId[Id, Loan]

  enum Status(val code: Int) extends EnumStatus[Int]:
    case Rent   extends Status(1)
    case Return extends Status(2)

  enum Result(val code: Int) extends EnumStatus[Int]:
    case Success extends Result(1)
    case Failure extends Result(2)
