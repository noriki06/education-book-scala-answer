package education.section0

object AnswerEx112:

  trait Animal:
    def name: String
    def sound: String

  case class Dog(
    name: String
  ) extends Animal:
    def sound = "ワン"

  def traitQuestion1(dog: Dog): String =
    s"${dog.name} は ${dog.sound} と鳴く"

  def main(args: Array[String]): Unit =
    println(traitQuestion1(Dog("ポチ")))
