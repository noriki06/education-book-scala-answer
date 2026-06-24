package education.section10

object Answer10:

  sealed trait Result[A]
  case class Ok[A](value: A)    extends Result[A]   // 成功（A 型の値を持つ）
  case class Ng[A](message: String) extends Result[A]   // 失敗（理由を持つi）

  def sealedQuestion2(result: Result[Int]): String =
    result match
      case Ok(n) => s"成功: $n"
      case Ng(a) => s"失敗: $a"


  def main(args: Array[String]): Unit =
    println(sealedQuestion2(Ok(42)))
    println(sealedQuestion2(Ng("数値ではありません")))
