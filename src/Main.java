public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Sample accounts for testing
        bank.addAccount(new Account("1001", "1234", "Kumarthi", 5000));
        bank.addAccount(new Account("1002", "4321", "Arun", 3000));

        ATM atm = new ATM(bank);
        atm.start();
    }
}