package education.section9

object AnswerCheckpointTransfer:

  // import scala.concurrent.{Future, Await}
  // import scala.concurrent.ExecutionContext.Implicits.global
  // import scala.concurrent.duration.*

  /**
   * 口座のケースクラス
   */
  case class Account(
    id: Int,      // 口座ID
    name: String, // 名義
    balance: Int  // 残高(円)
  )

  enum TypesOfTransferFailures:
    case AccountNotFound(remitterId: Int, receiverId: Int)   // 口座が見つからない
    case AmountIncorrect(transferAmount: Int)        // 振込金額が正しくない（0 以下）
    case NotEnoughBalance(transferAmount: Int, balance: Int) // 残高が足りない

  val accounts =
    Seq(
      Account(1, "田中", 5000),
      Account(2, "佐藤", 0),
      Account(3, "鈴木", 12000)
    )

  val accountMap = accounts.map(account => account.id -> account).toMap

  /**
   * 残高が足りるか確認する
   */
  def returnBalance(account: Account, transferAmount: Int): Either[TypesOfTransferFailures, Int] =
    (account, transferAmount) match
      case (account, amount) if amount <= 0
        => Left(TypesOfTransferFailures.AmountIncorrect(amount))
      case (account, amount) if amount > account.balance
        => Left(TypesOfTransferFailures.NotEnoughBalance(amount, account.balance))
      case _
        => Right(account.balance - transferAmount)

  def main(args: Array[String]): Unit =
  　// 問１
    println(returnBalance(Account(1, "田中", 5000), 3000))
    println(returnBalance(Account(1, "田中", 5000), 0))
    println(returnBalance(Account(1, "田中", 5000), 8000))
