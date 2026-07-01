package education.section0

object AnswerEx120:

  sealed trait Result[A]
  case class Ok[A](value: A)    extends Result[A]   // 成功（A 型の値を持つ）
  case class Ng[A](message: String) extends Result[A]   // 失敗（理由を持つ）

  def sealedQuestion2(result: Result[Int]): String =
    result match
      case Ok(a) => s"成功: ${a}"
      case Ng(a) => "失敗: 数値ではありません"

  def main(args: Array[String]): Unit =
    println(sealedQuestion2(Ng("数値ではありません")))
