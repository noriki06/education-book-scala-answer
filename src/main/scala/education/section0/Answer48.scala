package education.section0

object Answer48:

  enum Signal:
    case Red
    case Yellow
    case Green

  def main(args: Array[String]): Unit =
    println(enumQuestion1(Signal.Red))

  def enumQuestion1(signal: Signal): String =
    signal match
      case Signal.Red => "止まれ"
      case Signal.Yellow => "注意"
      case Signal.Green => "進め"
