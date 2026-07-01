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

  def message(result: Either[Error, (Int, Int)]): String =
    result match
      case Left(Error.NonPrice(money)) => s"振込金額が正しくない${money}"
      case Left(Error.NonBalance(balance)) => s"残高が足りない${balance}"
      case Left(Error.NonAccount(id)) => s"口座が見つからない(ID:${id})"
      case Right(senbal, recbal) => s"振込完了（送金元残高 ${senbal} / 送金先残高 ${recbal}）"

  def hurihuri(rrr: Seq[(Int, Int, Int)]): Future[Seq[Either[Error, (Int, Int)]]] =
    Future.sequence(rrr.map { (senderId: Int, receiverId: Int, money: Int) =>
      hurikomi(senderId: Int, receiverId: Int, money: Int) })


  def main(args: Array[String]): Unit =
    val result7: Seq[Either[Error, (Int, Int)]] = Await.result(hurihuri(Seq((1, 2, 5000), (3, 2, 1000), (1, 3, 10000))), Duration.Inf)
    result7.foreach(result => println(message(result)))
