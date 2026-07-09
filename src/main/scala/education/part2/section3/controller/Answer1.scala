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
object Answer1:
  def main(args: Array[String]): Unit =
    val controller = DIContainer.getInstance(classOf[Answer1Controller])
    val result: Seq[User.Id] = Await.result(controller.invoke(), 30.seconds)
    println(result)


/**
 * 問1 の処理本体（依存はコンストラクタ注入）
 */
@Singleton
class Answer1Controller @Inject()(edu: EduRepositoryFacade)(using ExecutionContext):
  def invoke(): Future[Seq[User.Id]] =
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
    } yield ids
