package education.section9

object AnswerCheckpointFuture:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  /**
   * 会員のケースクラス
   */

  case class Member(
    id:    Int,    // 会員ID
    name:  String, // 氏名
    point: Int     // 保有ポイント
  )

  // 引き落としの失敗の種類
  enum TypeOfPaymentFailed:
    case MemberNotFound(memberId: Int)  // 会員が見つからない（その会員IDが無い）
    case PointsIncorrect(requestedPoints: Int) // 利用ポイントが正しくない（0 以下だった）
    case NotEnoughPoints(heldPoints: Int) // ポイントが足りない

  // 会員マスタ一覧
  val members: Seq[Member] =
    Seq(
      Member(1, "田中", 500),
      Member(2, "佐藤", 0),
      Member(3, "鈴木", 1200)
    )

  val memberMap = members.map(member => member.id -> member).toMap

  def BalanceAfterUse(memberMap: Map[Int, Member], usePoints: Int): Either[TypeOfPaymentFailed, Int] =
    (memberMap, usePoints) match
      case (memberMap, usePoints) if usePoints <= 0
        => Left(TypeOfPaymentFailed.PointsIncorrect(usePoints))
      case (memberMap, usePoints) if memberMap.point > usePoints
        => Left(TypeOfPaymentFailed.NotEnoughPoints(memberMap.point))
      case _
        => Right(memberMap.point - usePoints)

  def describe(result: Either[TypeOfPaymentFailed, Int]): String =
    result match
      case Right(points)                             => s"$points"
      case Left(TypeOfPaymentFailed.PointsIncorrect) => "利用ポイント不正の失敗"
      case Left(TypeOfPaymentFailed.NotEnoughPoints) => "ポイント不足の失敗"

  def main(args: Array[String]): Unit =
    println(describe(BalanceAfterUse(Map(3, memberMap), 300)))
