import java.util.HashMap;

public class Bank {
    private final HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account findAccount(String userId) {
        return accounts.get(userId);
    }

    // Returns the account if ID and PIN match, otherwise null
    public Account authenticate(String userId, String pin) {
        Account acc = accounts.get(userId);
        if (acc != null && acc.checkPin(pin)) {
            return acc;
        }
        return null;
    }

    // Updates both accounts and logs the transaction on both
    public boolean transfer(Account from, Account to, double amount) {
        if (!from.withdraw(amount, "TRANSFER OUT")) {
            return false;
        }
        to.deposit(amount, "TRANSFER IN");
        return true;
    }
}
