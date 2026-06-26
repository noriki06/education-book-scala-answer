package education.section0

object Answer51:

  case class Book(title: String, price: Int)

  def main(args: Array[String]): Unit =
    println(ccEx1(Seq(Book("Scala入門", 3000), Book("Java基礎", 2500), Book("Go実践", 3500))))

  def ccEx1(books: Seq[Book]): Seq[String] =
    books.sortBy(book => book.price).map(book => book.title)
