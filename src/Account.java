import java.util.ArrayList;

public class Account {
    private final String userId;
    private final String pin;
    private final String name;
    private double balance;
    private final ArrayList<Transaction> history = new ArrayList<>();

    public Account(String userId, String pin, String name, double openingBalance) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.balance = openingBalance;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public ArrayList<Transaction> getHistory() { return history; }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void deposit(double amount, String type) {
        balance += amount;
        history.add(new Transaction(type, amount, balance));
    }

    // Returns false if amount is invalid or balance is too low
    public boolean withdraw(double amount, String type) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        history.add(new Transaction(type, amount, balance));
        return true;
    }
}
