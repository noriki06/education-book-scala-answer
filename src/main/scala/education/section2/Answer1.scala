package education.section2
object Answer1:
  def main(args: Array[String]): Unit =
    println(optionQuestion1(Some(5)))
    println(optionQuestion1(None))
  
  def optionQuestion1(num: Option[Int]): Int = 
    num.getOrElse(0)
     
