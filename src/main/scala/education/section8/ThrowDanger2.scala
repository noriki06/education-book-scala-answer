package education.section8

object ThrowDanger2:
  // 文字列を数値にする。失敗すると例外を投げる（s.toInt がそうなっている）
  def parseInt(s: String): Try[Int] =
    Try(s.toInt)

  def main(args: Array[String]): Unit =
    import scala.util.{Try, Success, Failure}

    val result: Try[Int] = parseInt()
    result
    println(parseInt("42") match
      case Success(n) => n.toInt
      case Failure(e) => "(変換できません)"
    )    // 数値 → 42
    println(parseInt("abc") match
      case Success(n) => n
      case Failure(e) => "(変換できません)"
    )   // 数値でない → ここで例外が発生
    println("最後まで到達した")   // この行は表示される？
