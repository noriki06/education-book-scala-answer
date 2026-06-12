package education.section5

object Answer1:
  def setQuestion1(members: Set[String], name: String): Boolean =
    members.contains(name)


  def main(args: Array[String]): Unit =
    println(setQuestion1(Set("Alice", "Bob"), "Alice"))
    println(setQuestion1(Set("Alice", "Bob"), "Carol"))
    println(Set("Alice", "Alice", "Bob"))
