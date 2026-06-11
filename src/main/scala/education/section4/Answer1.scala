package education.section4

object Answer1:
 
  def tupleQuestion1(name: String, score: Int): (String, Int) =
    (name, score)
    //name -> score：(これでも同じ答えが出ることを確認済み）)


  def main(args: Array[String]): Unit =
    println(tupleQuestion1("Alice", 80))
