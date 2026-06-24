package education.section10

object Answer3:

  trait Animal:
    def name: String
    def sound: String
    def greet: String = s"$name は $sound と鳴く"

  case class Dog(name: String) extends Animal:
    def sound = "ワン"


  case class Cat(name: String) extends Animal:
    def sound = "ニャー"
    override def greet = s"$name は気まぐれに $sound と鳴く"

  def traitQuestion3(cat: Cat): String =
    Cat("タマ").greet

  def main(args: Array[String]): Unit =
    println(traitQuestion3(Cat("タマ")))
