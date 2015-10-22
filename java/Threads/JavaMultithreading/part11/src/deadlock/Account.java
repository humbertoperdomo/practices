package deadlock;

public class Account {
  private int balance = 10000;

  public boolean deposit(int amount) {
    balance += amount;
    return true;
  }

  public boolean withdraw(int amount) { // throws IllegalArgumentException {

    if (amount <= balance) {
      balance -= amount;
      return true;
    } //else {
      //throw new IllegalArgumentException();
    //}
    return false;
  }

  public int getBalance() {
    return balance;
  }

  public static void transfer(Account acc1, Account acc2, int amount) { // throws IllegalArgumentException {
    if (acc1.withdraw(amount))
      acc2.deposit(amount);
  }
}
