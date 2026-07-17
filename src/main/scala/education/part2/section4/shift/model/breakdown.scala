package education.part2.section4.shift.model

import ixias.core.model.*

/**
 * 会員エンティティ（前章で定義。DB を何も知らない純粋な case class）
 */
case class BD(
  id:           Option[BD.Id], // ID
  actualStart:  LocalDateTime, // 実勤務の開始
  actualEnd:    LocalDateTime, // 実勤務の終了
  allTime:      Duration,      // 総時間
  overTime:     Duration,      // 超過時間
  lateNight:    Duration,      // 深夜時間
) extends EntityModel[BD.Id]

object BD:
  object Id extends EntityId[Long]
  type Id         = Id.Repr
  type WithNoId   = Entity.WithNoId[Id, BD]
  type EmbeddedId = Entity.EmbeddedId[Id, BD]
