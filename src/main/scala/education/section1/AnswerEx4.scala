package education.section1

object AnswerEx4:

  class BankAccount(val balance: Int):
    def deposit(amount: Int): BankAccount = new BankAccount(balance + amount)
    def withdraw(amount: Int): BankAccount =
      if balance >= amount then new BankAccount(balance - amount) else this



  def main(args: Array[String]): Unit =
    val account = BankAccount(1000)
    val result  = account.deposit(500).withdraw(2000).withdraw(300)
    println(result.balance)
