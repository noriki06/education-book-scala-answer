package education.section0

object AnswerEx113:

  trait Animal:
    def name: String
    def sound: String
    def greet: String = s"$name は $sound と鳴く"

  case class Dog(name: String) extends Animal:
    def sound = "ワン"

  case class Cat(name: String) extends Animal:
    def sound = "ニャー"

  def traitQuestion2(): Seq[String] =
    Seq(Dog("ポチ").greet, Cat("タマ").greet)

  def main(args: Array[String]): Unit =
    println(traitQuestion2())
