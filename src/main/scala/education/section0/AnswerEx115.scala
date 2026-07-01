package education.section0

object AnswerEx115:

  trait Named:
    def name: String

  trait Aged:
    def age: Int
    def isAdult: Boolean = age >= 18

  case class Person(name: String, age: Int) extends Named, Aged

  def traitQuestion4(person: Person): String =
    if person.isAdult then s"${person.name} さんは成人です"
    else s"${person.name} さんは未成年です"
  def main(args: Array[String]): Unit =
    println(traitQuestion4(Person("Alice", 20)))
