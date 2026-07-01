package education.section0

object Answer114:

  trait Animal:
    def name: String
    def sound: String
    def greet: String = s"$name は $sound と鳴く"

  case class Dog(name: String) extends Animal:
    def sound = "ワン"


  case class Cat(name: String) extends Animal:
    def sound = "ニャー"
    override def greet = s"$name は気まぐれに $sound と鳴く"

  def traitQuestion2(): Seq[String] =
    Seq(Dog("ポチ").greet, Cat("タマ").greet)

  def main(args: Array[String]): Unit =
    println(traitQuestion2())
