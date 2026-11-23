import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime time;

    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.time = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("%s | %-12s | Amount: %10.2f | Balance: %10.2f",
                time.format(f), type, amount, balanceAfter);
    }
}