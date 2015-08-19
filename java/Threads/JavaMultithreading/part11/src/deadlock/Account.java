package deadlock;

public class Account {
  private int balance = 10000;

  public void deposit(int amount) {
    balance += amount;
  }

  public void withdraw(int amount) throws IllegalArgumentException {
    if (amount <= balance) {
      balance -= amount;
    } else {
      throw new IllegalArgumentException();
    }
  }

  public int getBalance() {
    return balance;
  }

  public static void transfer(Account acc1, Account acc2, int amount) throws IllegalArgumentException {
    acc1.withdraw(amount);
    acc2.deposit(amount);
  }
}
