package education.section0

object Answer56:

  def main(args: Array[String]): Unit =
    println(setQuestion1(Set("Alice", "Bob"), "Alice"))

  def setQuestion1(members: Set[String], name: String): Boolean =
    members.contains(name)
