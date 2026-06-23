package education.section9

object AnswerCheckpointTransfer:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  /**
   * 口座のケースクラス
   */
  case class Account(
    id: Int,      // 口座ID
    name: String, // 名義
    balance: Int  // 残高(円)
  )

  enum TypesOfTransferFailures:
    case AccountNotFound(remitterId: Int)   // 口座が見つからない
    case AmountIncorrect(transferAmount: Int)                // 振込金額が正しくない（0 以下）
    case NotEnoughBalance(transferAmount: Int, balance: Int) // 残高が足りない

  val accounts =
    Seq(
      Account(1, "田中", 5000),
      Account(2, "佐藤", 0),
      Account(3, "鈴木", 12000)
    )

  val accountMap = accounts.map(account => account.id -> account).toMap

  /**
   * 問2:残高が足りるか確認する
   */
  def returnBalance(account: Account, transferAmount: Int): Either[TypesOfTransferFailures, Int] =
    if transferAmount <= 0
    // 振込金額が 0 以下
      then Left(TypesOfTransferFailures.AmountIncorrect(transferAmount))
    else if transferAmount > account.balance
    // 残高より振込金額が多い
      then Left(TypesOfTransferFailures.NotEnoughBalance(transferAmount, account.balance))
    else
    // 振込後の送金元残高（残高 − 振込金額）を返す
      Right(account.balance - transferAmount)

  /**
   * 問3:口座を探す
   */
  def serchAccountById(memberMap: Map[Int, Account], accountId: Int):
    Future[Either[TypesOfTransferFailures, Account]] =
    Future {
      Thread.sleep(1000)
      memberMap
        .get(accountId)
        .toRight(TypesOfTransferFailures.AccountNotFound(accountId))

    }

  def main(args: Array[String]): Unit =
    // 問2
    println(returnBalance(Account(1, "田中", 5000), 3000))
    println(returnBalance(Account(1, "田中", 5000), 0))
    println(returnBalance(Account(1, "田中", 5000), 8000))
    //問3
    val result1: Either[TypesOfTransferFailures, Account] = Await.result(serchAccountById(accountMap, 1), Duration.Inf)
    println(result1)
