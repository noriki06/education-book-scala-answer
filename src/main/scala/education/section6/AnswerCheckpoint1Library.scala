package education.section6

object AnswerCheckpoint1Library:

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
      case Novel,
           Comic,
           Technical,
           Magazine

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
      case OnLoan,   //貸出中
           Returned, //返却済み
           Overdue   // 延滞中

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
      Loan(Loan.Id(105), Book.Id(2), "Alice", Loan.Status.Returned),
    )

  def main(args: Array[String]): Unit =
    //問１
    println(books)
    println(loans)
    //問２
    val byId = booksById(books)
    println(findBookTitle(byId, Book.Id(3)).getOrElse("不明"))
    println(findBookTitle(byId, Book.Id(99)).getOrElse("不明"))
    //問３
    println(totalBybook(loans))

  /**
   *Seqのままだと探すたびに先頭から見るので、ID で引ける Map に変換
   */
  def booksById(books: Seq[Book]): Map[Book.Id, Book] =
    books
      .map(book => book.id -> book)
      .toMap

  /**
   *booksByIdで作ったMapとBook.Idを受け取り、タイトルを返す
   */
  def findBookTitle(byId: Map[Book.Id, Book], id: Book.Id): Option[String] =
    byId
      .get(id)
      .map(book => book.title)

  /**
   *本ごとの貸出回数を集計する
   */
  def totalBybook(loans: Seq[Loan]): Map[Book.Id, Int] =
    val groupedLoansByBookid =
      loans
        .groupBy(loan => loan.bookId)

    groupedLoansByBookid
      .view.mapValues(loanbook => loanbook.size)
      .toMap
