package education.section4

object AnswerEx1:
  case class Book(title: String, price: Int)

  def ccEx1(books: Seq[Book]): Seq[String] =
    books.sortBy(b => b.price).map(b => b.title)

         
  def main(args: Array[String]): Unit =
    println(ccEx1(Seq(Book("Scala入門", 3000), Book("Java基礎", 2500), Book("Go実践", 3500))))

