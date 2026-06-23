package education.section9

object AnswerCheckpointSignup:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  // 「中の処理は時間がかかる」を表すラッパー（DB アクセスのつもり）。
  // block は呼ばれた時点ではまだ実行されず、1 秒待ってから実行される（名前渡し: => A）。
  def heavyProcess[A](block: => A): Future[A] =
    Future { Thread.sleep(1000); block }

  // 応募情報（DB に合わせて型は固定）
  case class Application(name: String, email: String, phone: String)

  // 既存の登録（登録ID -> 応募情報）。重複チェックの題材になる。
  val db: Map[Int, Application] = Map(
    1 -> Application("田中", "tanaka@example.com", "090-1111-2222"),
    2 -> Application("佐藤", "sato@example.com", "08033334444"),
  )

  // 動作確認用の入力（main で使う）
  val ok       = Application("鈴木", "suzuki@example.com", "070-5555-6666")  // 正常
  val dupEmail = Application("重複", "tanaka@example.com", "09000000000")    // 既存と同じメール
  val badEmail = Application("無効", "bad-email-no-at",    "09000000000")    // @ がない
  val badPhone = Application("無効", "ok@example.com",     "090-abcd")       // 数字でない

  enum ErrorType:
    case EmailIncorrect                    // メールアドレスの形式が正しくない
    case PhoneNumberIncorrect              //電話番号の形式が正しくない
    case AlreadyRegistered(address: String) // すでに登録済み（同じメールアドレスの登録がある）

  def checkEmailFormat(application: Application): Either[ErrorType, Application] =
    if application.email.contains("@")
      then Right(application.email)
    else
      then Left(ErrorType.EmailIncorrect)
