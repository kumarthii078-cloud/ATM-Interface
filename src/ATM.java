import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static final int MAX_ATTEMPTS = 3;
    private final Bank bank;
    private final Scanner sc = new Scanner(System.in);

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        System.out.println("===== WELCOME TO THE ATM =====");
        Account account = login();
        if (account == null) {
            System.out.println("Too many incorrect attempts. Access denied.");
            sc.close();
            return;
        }
        System.out.println("\nLogin successful. Hello, " + account.getName() + "!");
        showMenu(account);
        sc.close();
    }

    private Account login() {
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            System.out.print("Enter User ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Enter PIN: ");
            String pin = sc.nextLine().trim();

            Account acc = bank.authenticate(id, pin);
            if (acc != null) {
                return acc;
            }
            System.out.println("Invalid User ID or PIN. Attempts left: " + (MAX_ATTEMPTS - i));
        }
        return null;
    }

    private void showMenu(Account account) {
        boolean running = true;
        while (running) {
            System.out.println("\n------ ATM MENU ------");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    showHistory(account);
                    break;
                case "2":
                    withdraw(account);
                    break;
                case "3":
                    deposit(account);
                    break;
                case "4":
                    transfer(account);
                    break;
                case "5":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }
        }
    }

    private void showHistory(Account account) {
        ArrayList<Transaction> history = account.getHistory();
        System.out.println("\n--- Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : history) {
                System.out.println(t);
            }
        }
        System.out.printf("Current balance: %.2f%n", account.getBalance());
    }

    private void withdraw(Account account) {
        double amount = readAmount("Enter amount to withdraw: ");
        if (amount <= 0) return;
        if (amount > account.getBalance()) {
            System.out.println("Insufficient Funds!");
            return;
        }
        account.withdraw(amount, "WITHDRAW");
        System.out.printf("Please collect your cash. New balance: %.2f%n", account.getBalance());
    }

    private void deposit(Account account) {
        double amount = readAmount("Enter amount to deposit: ");
        if (amount <= 0) return;
        account.deposit(amount, "DEPOSIT");
        System.out.printf("Deposit successful. New balance: %.2f%n", account.getBalance());
    }

    private void transfer(Account account) {
        System.out.print("Enter recipient Account ID: ");
        String id = sc.nextLine().trim();
        Account recipient = bank.findAccount(id);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }
        if (recipient == account) {
            System.out.println("You cannot transfer to your own account.");
            return;
        }
        double amount = readAmount("Enter amount to transfer: ");
        if (amount <= 0) return;
        if (amount > account.getBalance()) {
            System.out.println("Insufficient Funds!");
            return;
        }
        bank.transfer(account, recipient, amount);
        System.out.printf("Transferred %.2f to %s. New balance: %.2f%n",
                amount, recipient.getName(), account.getBalance());
    }

    // Reads a positive number safely; returns -1 if invalid
    private double readAmount(String prompt) {
        System.out.print(prompt);
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return -1;
        }
    }
}