package education.part2.section3.controller

import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.*
import ixias.core.model.*
import education.part2.section3.DIContainer
import education.part2.section3.model.User
import education.part2.section3.persistence.EduRepositoryFacade

/**
 * 問1 の入口：コントローラを 1 個取り出して invoke() を呼ぶ
 */
object Answer5:
  def main(args: Array[String]): Unit =
    val controller = DIContainer.getInstance(classOf[Answer5Controller])
    val result: Unit = Await.result(controller.invoke(), 60.seconds)
    println(result)


/**
 * 問1 の処理本体（依存はコンストラクタ注入）
 */
@Singleton
class Answer5Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  def invoke(): Future[Unit] =
    val alice = User(
                  None,
                  "Alice",
                  EmailAddress("alice@example.com"),
                  User.Status.Active
                  ).toWithNoId
    val bob = User(
                None,
                "Bob",
                EmailAddress("bob@example.com"),
                User.Status.Active
                ).toWithNoId
    val noriki = User(
                   None,
                   "noriki",
                   EmailAddress("noriki@example.com"),
                   User.Status.Active
                   ).toWithNoId
    val seq = Seq(alice, bob, noriki)
    for {
      ids <- Future.sequence(seq.map(edu.user.add))

    // ② 取得：ID を持つ EmbeddedId が Option で返る
      found   <- edu.user.find(ids.head)

      // ③ 更新：取得できていれば、中の User を書き換えて渡す。戻り値は「更新前」の値
      before  <- found match
                 case Some(u) => edu.user.update(u.map(_.copy(state = User.Status.Withdrawn)))
                 case None    => Future.successful(None)
      users <- edu.user.filter(ids)
      _ = users.foreach(u => println(s"${u.v.name}：${u.v.state}"))
      _ = users.filter(_.v.state == User.Status.Withdrawn).map(_.id)
      deleted <- edu.user.delete(Seq(ids))
      - = users.foreach(u => println(s"${u.v.name}：${u.v.state}"))
    } yield ()
