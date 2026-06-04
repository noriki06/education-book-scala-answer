package education.section1
object PreferVal2:
  def main(args: Array[String]): Unit =
    println(new Counter(0).increment.increment.count)
  
  class CounterMutable:
    var count: Int = 0
    def increment(): Unit =
  
      count = count + 1
  class Counter (val count: Int = 0):
    def increment: Counter =
      new Counter (count + 1)
