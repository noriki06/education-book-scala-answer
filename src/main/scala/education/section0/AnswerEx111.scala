package education.section0

object AnswerEx111:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  // 「中の処理は時間がかかる」を表すラッパー（DB アクセスのつもり）。
  // block は呼ばれた時点ではまだ実行されず、1 秒待ってから実行される（名前渡し: => A）。
  def heavyProcess[A](block: => A): Future[A] =
    Future { Thread.sleep(1000); block }

  // 応募情報（DB に合わせて型は固定）
  case class Apply(name: String, email: String, phone: String)

  // 既存の登録（登録ID -> 応募情報）。重複チェックの題材になる。
  val db: Map[Int, Apply] = Map(
    1 -> Apply("田中", "tanaka@example.com", "090-1111-2222"),
    2 -> Apply("佐藤", "sato@example.com", "08033334444"),
  )

  // 動作確認用の入力（main で使う）
  val ok       = Apply("鈴木", "suzuki@example.com", "070-5555-6666")  // 正常
  val dupEmail = Apply("重複", "tanaka@example.com", "09000000000")    // 既存と同じメール
  val badEmail = Apply("無効", "bad-email-no-at",    "09000000000")    // @ がない
  val badPhone = Apply("無効", "ok@example.com",     "090-abcd")       // 数字でない

  enum Error:
    case Nonmail
    case Nonphone
    case Nonresi

  def checkmail(apply: Apply): Either[Error, Apply] =
    if apply.email.contains("@") then Right(apply)
    else Left(Error.Nonmail)

  def checkphone(apply: Apply): Either[Error, Apply] =
    if apply.phone.replace("-", "").forall(_.isDigit) then Right(apply)
    else Left(Error.Nonphone)

  def checkresi(apply: Apply): Future[Either[Error, Apply]] =
    heavyProcess { db.values.find(_.email == apply.email) }
      .map {
        case Some(n) => Left(Error.Nonresi)
        case None => Right(apply)
      }

  def addresi(apply: Apply): Future[Either[Error, Int]] =
    heavyProcess { db.keys.maxOption.getOrElse(0) + 1 }
      .map(id => Right(id))

  def total(apply: Apply): Future[Either[Error, Int]] =
    val check: Either[Error, Apply] =
      for {
        a <- checkmail(apply)
        b <- checkphone(a)
      } yield b

    check match
      case Left(e) => Future.successful(Left(e))
      case Right(apply) =>
        checkresi(apply).flatMap {
          case Left(e) => Future.successful(Left(e))
          case Right(apply) => addresi(apply)
        }

  def message(result: Either[Error, Int]): String =
    result match
      case Left(Error.Nonmail) => "メールアドレスの形式が正しくない"
      case Left(Error.Nonphone) => "電話番号の形式が正しくない"
      case Left(Error.Nonresi) => "すでに登録されています"
      case Right(id) => s"登録完了（登録ID: ${id}）"

  def totalapply(applys: Seq[(Apply)]): Future[Seq[Either[Error, Int]]] =
    Future.sequence(applys
      .map(apply => total(apply)))






  def main(args: Array[String]): Unit =
    val result7: Seq[Either[Error, Int]] = Await.result(totalapply(
      Seq(
        Apply("鈴木", "suzuki@example.com", "070-5555-6666"),
        Apply("重複", "tanaka@example.com", "09000000000"),
        Apply("無効", "bad-email-no-at",    "09000000000"),
        Apply("無効", "ok@example.com",     "090-abcd"))),
        Duration.Inf)
    result7.foreach(result => println(message(result)))
