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
      case OnLoan,
           Returned,
           Overdue

  def main(args: Array[String]): Unit =
    println(books)
    println(loans)

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
