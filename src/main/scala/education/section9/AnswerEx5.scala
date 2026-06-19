package education.section9

object AnswerEx5:

  import scala.concurrent.{Future, Await}
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.*

  case class User(
    id: Int,
    name: String
  )

  val users: Seq[User] =
    Seq(
      User(1, ohira),
      User(2, ueno),
      User(3, tenkumo)
    )

  val userMap = users.map(user => user.id -> user).toMap

  def findUser(id: Int): Future[Either[String, User]] =
    Future{
      Thread.sleep(1000)
      userMap
        .get(id)
        .toRight("ユーザーが見つかりません: id=$id")

  def main(args: Array[String]): Unit =
    val start1 = System.currentTimeMillis()
    val result1: Int = Await.result1(findUser(1), Duration.Inf)
    val end1 = System.currentTimeMillis()
    println(s"結果: $result1, かかった時間: ${end1 - start1} ms")

    val start2 = System.currentTimeMillis()
    val result2: Int = Await.result2(findUser(5), Duration.Inf)
    val end2 = System.currentTimeMillis()
    println(s"結果: $result2, かかった時間: ${end2 - start2} ms")
