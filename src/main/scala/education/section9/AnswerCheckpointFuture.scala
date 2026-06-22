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
    case MemberNotFound(memberId: Int)         // 会員が見つからない（その会員IDが無い）
    case PointsIncorrect(requestedPoints: Int) // 利用ポイントが正しくない（0 以下だった）
    case NotEnoughPoints(heldPoints: Int, usePoints: Int)      // ポイントが足りない

  // 会員マスタ一覧
  val members: Seq[Member] =
    Seq(
      Member(1, "田中", 500),
      Member(2, "佐藤", 0),
      Member(3, "鈴木", 1200)
    )

  val memberMap = members.map(member => member.id -> member).toMap // 会員IDで引ける会員マスター一覧

  /**
   * 問2:ポイントが足りるか確認する
   */
  def balanceAfterUse(member: Member, usePoints: Int): Either[TypeOfPaymentFailed, Int] =
    (member, usePoints) match
      case (member, usePoints) if usePoints <= 0
        => Left(TypeOfPaymentFailed.PointsIncorrect(usePoints))
      case (member, usePoints) if member.point < usePoints
        => Left(TypeOfPaymentFailed.NotEnoughPoints(member.point, usePoints))
      case _
        => Right(member.point - usePoints)

  /**
   * balanceAfterUseの値から利用後の残高 or エラー表示
   */
  def describe(result: Either[TypeOfPaymentFailed, Int]): String =
    result match
      case Right(points)
        => s"残高 $points ポイント"
      case Left(TypeOfPaymentFailed.PointsIncorrect(usePoints))
        => s"${usePoints}は不正な利用ポイントです"
      case Left(TypeOfPaymentFailed.NotEnoughPoints(heldPoints, usePoints))
        => s"ポイントが足りません（保有 $heldPoints／利用 $usePoints）"
      case Left(TypeOfPaymentFailed.MemberNotFound(id))
        => s"ID:${id}の会員はいません"

  /**
   * 問 3: 会員を探す
   */
  def findMemberById(memberMap: Map[Int, Member], id: Int): Future[Either[TypeOfPaymentFailed, Member]] =
    Future {
      Thread.sleep(1000)
      memberMap
        .get(id)
        .toRight(TypeOfPaymentFailed.MemberNotFound(id))
    }


  /**
   * 問 4: 2 つをつないで引き落としを行う
   */
  def transaction(id: Int, usePoints: Int): Future[Either[TypeOfPaymentFailed, Int]] =

    findMemberById(memberMap, id)
      .map(member =>
          member.flatMap(memberInfo =>
            balanceAfterUse(memberInfo, usePoints)
          )
      )

  def tryAll(requests: Seq[(Int, Int)]): Future[Seq[Either[TypeOfPaymentFailed, Int]]] =
    Future.sequence(requests.map((id, usePoints) => transaction(id, usePoints)))

  def main(args: Array[String]): Unit =
    // 問2
    // println(describe(balanceAfterUse(Member(1, "田中", 500), 300)))
    //println(describe(balanceAfterUse(Member(1, "田中", 500), 0  )))
    //println(describe(balanceAfterUse(Member(2, "佐藤", 0),   300)))
    //println(describe(balanceAfterUse(Member(6, "山田", 0),   300)))
    // 問3
    //val result1: Either[TypeOfPaymentFailed, Member] = Await.result(findMemberById(memberMap, 1), Duration.Inf)
    //println(result1)
    //val result2: Either[TypeOfPaymentFailed, Member] = Await.result(findMemberById(memberMap, 99), Duration.Inf)
    //println(result2)
    // 問4
    //val result3: Either[TypeOfPaymentFailed, Int] = Await.result(transaction(1, 300),  Duration.Inf)
    //println(result3)
    //val result4: Either[TypeOfPaymentFailed, Int] = Await.result(transaction(2, 100),  Duration.Inf)
    //println(result4)
    //val result5: Either[TypeOfPaymentFailed, Int] = Await.result(transaction(1, 0),    Duration.Inf)
    //println(result5)
    //val result6: Either[TypeOfPaymentFailed, Int] = Await.result(transaction(99, 100), Duration.Inf)
    //println(result6)
    // 問５
    val result6: Seq[Either[TypeOfPaymentFailed, Int]] = Await.result(tryAll(Seq((1, 300), (2, 100), (1, 0), (99, 100))), Duration.Inf)
    result6.foreach(result => println(describe(result)))
