package education.section4

object Answer8:
  enum Signal:
      case Red, Yellow, Green

  def enumQuestion1(signal: Signal): String =
    signal match
      case Signal.Red => "止まれ"
      case Signal.Yellow => "注意"
      case Signal.Green => "進め"
         
  def main(args: Array[String]): Unit =
    println(enumQuestion1(Signal.Red))
    println(enumQuestion1(Signal.Green))
