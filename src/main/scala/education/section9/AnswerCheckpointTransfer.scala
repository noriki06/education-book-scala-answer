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
    case AccountNotFound(RemitterId: Int, ReceiverId: Int)   // 口座が見つからない
    case TransferAmountIncorrect(TransferAmount: Int)        // 振込金額が正しくない（0 以下）
    case NotEnoughBalance(TransferAmount: Int, Balance: Int) // 残高が足りない

  val accounts =
    Seq(
      Account(1, "田中", 5000),
      Account(2, "佐藤", 0),
      Account(3, "鈴木", 12000)
    )

  val accountMap = accounts.map(account => account.id -> account).toMap
