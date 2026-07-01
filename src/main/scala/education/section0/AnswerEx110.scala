package education.section0

object AnswerEx110:
  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  case class Account(
    id: Int,
    name: String,
    balance: Int
  )

  enum Error:
    case NonAccount(id: Int)
    case NonPrice(price: Int)
    case NonBalance(balance: Int)

  val accounts: Seq[Account] =
    Seq(
      Account(1, "田中", 5000),
      Account(2, "佐藤", 0),
      Account(3, "鈴木", 12000)
    )

  val accountMap = accounts.map(account => account.id -> account).toMap

  def checkBalance(account: Account, money: Int): Either[Error, Int] =
    (account, money) match
      case (account, money) if money <= 0 => Left(Error.NonPrice(money))
      case (account, money) if money > account.balance => Left(Error.NonBalance(account.balance))
      case _ => Right(account.balance - money)

  def findAccount(accountMap: Map[Int, Account], id: Int): Future[Either[Error, Account]] =
    Future {
      Thread.sleep(1000)
      accountMap.get(id).toRight(Error.NonAccount(id))
    }

  def hurikomi(senderId: Int, receiverId: Int, money: Int): Future[Either[Error, (Int, Int)]] =
    findAccount(accountMap, senderId).flatMap {
      case Left(e) => Future.successful(Left(e))
      case Right(sendAccount) =>
        checkBalance(sendAccount, money) match {
          case Left(e) => Future.successful(Left(e))
          case Right(newBalance) =>
            findAccount(accountMap, money).map {
              case Left(e) => Left(e)
              case Right(receiverAccount) => Right((newBalance, receiverAccount.balance + money))
            }
        }
    }




  def main(args: Array[String]): Unit =
    println(Await.result(hurikomi(1, 3, 3000), Duration.Inf))
