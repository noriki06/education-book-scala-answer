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

  val findById = members.map(member => member.id -> member).toMap

  def main(args: Array[String]): Unit =
