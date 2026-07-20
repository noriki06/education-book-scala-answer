package education.part2.section4.shift.model

import ixias.core.model.*

/**
 * 勤務実績エンティティ（前章で定義。DB を何も知らない純粋な case class）
 */
case class Work(
  id:        Option[Work.Id],         // 勤務id
  name:      String,                  // シッター名
  planStart: LocalDateTime,           // 予定の開始
  planEnd: LocalDateTime,             // 予定の終了
  actualStart: Option[LocalDateTime], // 実勤務の開始
  actualEnd: Option[LocalDateTime],   // 実勤務の終了
  status: Work.Status,                // 勤務の状態
  updatedAt: LocalDateTime = Now,     // 更新日時
  createdAt: LocalDateTime = Now      // 作成日時
) extends EntityModel[Work.Id]

object Work:
  object Id extends EntityId[Long]
  type Id         = Id.Repr
  type WithNoId   = Entity.WithNoId[Id, Work]
  type EmbeddedId = Entity.EmbeddedId[Id, Work]

  enum Status(val code: Int) extends EnumStatus[Int]:
    case plan    extends Status(1)
    case active  extends Status(2)
    case end     extends Status(3)
