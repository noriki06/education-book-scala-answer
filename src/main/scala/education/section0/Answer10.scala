package education.section0

object Answer10:

  def main(args: Array[String]): Unit =
    val account = BankAccount(1000)
    val result  = account.deposit(500).withdraw(2000).withdraw(300)
    println(result.balance)

  class BankAccount(val balance: Int):
    def deposit(amount: Int): BankAccount =
      BankAccount(balance + amount)

    def withdraw(amount: Int): BankAccount =
      if balance <=amount then this
      else                     BankAccount(balance - amount)
