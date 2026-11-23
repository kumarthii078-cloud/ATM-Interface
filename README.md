# ATM Interface

A console-based ATM simulation built in Java using object-oriented programming.

## Features
- Login with User ID and PIN (access denied after 3 wrong attempts)
- Transaction History
- Withdraw (with insufficient funds check)
- Deposit
- Transfer to another account
- Quit

## Classes
- `Main` – starts the program and creates sample accounts
- `ATM` – login, menu and user interaction
- `Bank` – stores accounts, authenticates users, handles transfers
- `Account` – balance, PIN and transaction list
- `Transaction` – one record (type, amount, balance, time)

## Tech Used
Java, OOP (encapsulation), ArrayList, HashMap, switch-case

## How to Run
1. Clone the repo
2. Open the `src` folder
3. Compile: `javac *.java`
4. Run: `java Main`

## Test Login
- User ID: `1001`, PIN: `1234`
- User ID: `1002`, PIN: `4321`
