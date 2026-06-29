package education.section0

object Answer74:

  case class Book(
    id: Book.Id,
    title: String,
    category: Book.Category
  )
  object Book:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

    enum Category:
      case Novel
      case Comic
      case Technical
      case Magazine

  case class Loan(
    id: Loan.Id,
    bookId: Book.Id,
    borrower: String,
    status: Loan.Status
  )


  object Loan:
    opaque type Id = Long
    object Id:
      def apply(value: Long): Id = value

    enum Status:
      case OnLoan
      case Returned
      case Overdue

  def main(args: Array[String]): Unit =
    val books: Seq[Book] =
      Seq(
        Book(Book.Id(1), "Scala入門", Book.Category.Technical),
        Book(Book.Id(2), "名探偵コナン1", Book.Category.Comic),
        Book(Book.Id(3), "吾輩は猫である", Book.Category.Novel),
        Book(Book.Id(4), "週刊ジャンプ", Book.Category.Magazine),
        Book(Book.Id(5), "リファクタリング", Book.Category.Technical)
      )

    val loans: Seq[Loan] =
      Seq(
        Loan(Loan.Id(101), Book.Id(1), "Alice", Loan.Status.Returned),
        Loan(Loan.Id(102), Book.Id(2), "Bob", Loan.Status.OnLoan),
        Loan(Loan.Id(103), Book.Id(1), "Carol", Loan.Status.OnLoan),
        Loan(Loan.Id(104), Book.Id(3), "Alice", Loan.Status.Overdue),
        Loan(Loan.Id(105), Book.Id(2), "Alice", Loan.Status.Returned)
      )

    println(books)
    println(loans)

    val byId = bookMap(books)
    println(findTitle(byId, Book.Id(3)))

    println(totalLoan(loans))

    println(neverBook(books, loans))


  def bookMap(books: Seq[Book]): Map[Book.Id, Book] =
    books.map(book => book.id -> book).toMap

  def findTitle(byId: Map[Book.Id, Book], id: Book.Id): Option[String] =
    byId.get(id).map(book =>  book.title)

  def totalLoan(loans: Seq[Loan]): Map[Book.Id, Int] =
    loans.groupBy(loan => loan.bookId).view.mapValues(book => book.size).toMap

  def neverBook(books: Seq[Book], loans: Seq[Loan]): Set[String] =
    val dobooks = loans.map(loan => loan.bookId).toSet
    val allbooks = books.map(book => book.id).toSet

    val neverbooks = allbooks.diff(dobooks)

    books.filter(book => neverbooks.contains(book.id)).map(book => book.title).toSet
