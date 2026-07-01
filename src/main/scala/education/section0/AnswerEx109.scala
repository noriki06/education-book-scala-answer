package education.section0

object AnswerEx109:
  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  case class Member(
    id: Int,
    name: String,
    point:Int
  )

  enum Error:
    case NonMember(id: Int)
    case NonNumber(number: Int)
    case NonPoint(point: Int)

  def main(args: Array[String]): Unit =
    val result3: String = Await.result(message(balance(2, 300)),  Duration.Inf)
    println(result3)

  val members: Seq[Member] =
    Seq(
      Member(1, "田中", 500),
      Member(2, "佐藤", 0),
      Member(3, "鈴木", 1200)
    )

  val memberMap = members.map(member => member.id -> member).toMap


  def checkPoint(member: Member, usePoint: Int): Either[Error, Int] =
    (member, usePoint) match
      case (member, usePoint) if usePoint <= 0 => Left(Error.NonNumber(usePoint))
      case (member, usePoint) if member.point < usePoint => Left(Error.NonPoint(member.point))
      case _ => Right(member.point - usePoint)

  def findMember(memberMap: Map[Int, Member], id: Int): Future[Either[Error, Member]] =
    Future {
      Thread.sleep(1000)
      memberMap.get(id).toRight(Error.NonMember(id))
    }

  def balance(id: Int, usePoint: Int): Future[Either[Error, Int]] =
    findMember(memberMap, id).map(member => member.flatMap(memberResult => checkPoint(memberResult, usePoint)))


  def message(result: Future[Either[Error, Int]]): String =
    result match
      case Right(points)
        => s"残高 $points ポイント"
      case Left(Error.NonNumber(usePoints))
        => s"${usePoints}は不正な利用ポイントです"
      case Left(Error.NonPoint(point))
        => s"ポイントが足りません($point）"
      case Left(Error.NonMember(id))
        => s"ID:${id}の会員はいません"
