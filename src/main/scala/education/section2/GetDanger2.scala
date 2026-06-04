package education.section2

object GetDanger2:
  // id が 1 のユーザーだけ存在する想定（いなければ None）
  def findUserName(id: Int): Option[String] =
    if id == 1 then Some("Taro") else None

  def main(args: Array[String]): Unit =
    println(findUserName(1).getOrElse("(不明)"))   // 存在する → "Taro"
    println(findUserName(2).getOrElse("(不明)"))   // 存在しない → ここで例外が発生
    println("最後まで到達した")      // この行は表示される？
