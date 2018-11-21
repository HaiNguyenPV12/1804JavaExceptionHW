package JavaException;

import java.util.Scanner;

/**
 * Bank
 */
public class Bank {

    Account[] Accounts;
    Account oAccount;
    int maximumAccounts;
    int nextAccountNumber; // specifies the no in array for next account.
    int nextAccount = 0; // index
    Scanner input;
    String customerName;
    int accountNumber;
    double accountBalance;
    boolean check = true;

    public Bank() {
        input = new Scanner(System.in);
        System.out.print("Enter Number of accounts: ");
        while (true) {
            try {
                maximumAccounts = Integer.parseInt(input.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Please enter a number: ");
            }
        }
        Accounts = new Account[maximumAccounts];
    }

    private void displayAccountDetails(Account oAccount) {
        System.out.println("Account ID:   " + oAccount.getAccountNumber());
        System.out.println("Account Name: " + oAccount.getCustomerName());
        System.out.println("Balance:      " + oAccount.getAccountBalance() + "\n");
    }

    public void createAccount() {
        oAccount = new Account();
        if (maximumAccounts == nextAccount) {
            System.out.println("\nArray is full.\n");
            return;
        }
        input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter name: ");
                customerName = input.nextLine();
                oAccount.setCustomerName(customerName);
                if (customerName.isEmpty()) {
                    throw new Exception("Name can't be empty.");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        check = true;
        System.out.print("Enter Account ID: ");
        do {
            try {
                accountNumber = Integer.parseInt(input.nextLine());
                for (int i = 0; i <= nextAccount; i++) {
                    if (i == nextAccount) {
                        break;
                    } else if (accountNumber == Accounts[i].getAccountNumber()) {
                        throw new Exception("Account with ID already exists.");
                    } else if (i == Accounts.length) {
                        break;
                    }
                }
                oAccount.setAccountNumber(accountNumber);
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("ERROR! Must be a number.");
                System.out.print("ID: ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print("ID: ");
            }
        } while (check);

        check = true;
        System.out.print("Enter Account Balance: ");
        do {
            try {
                accountBalance = Double.parseDouble(input.nextLine());
                oAccount.setAccountBalance(accountBalance);
                check = false;
            } catch (Exception e) {
                System.out.println("ERROR! Must be a number.");
                System.out.print("Balance: ");
            }
        } while (check);
        System.out.println("");
        displayAccountDetails(oAccount);
        Accounts[nextAccount++] = oAccount;
    }

    public void withdraw() {
        if (nextAccount == 0) {
            System.out.println("\nArray is empty\n");
            return;
        }
        int accountNumberS;
        double withdraw = 0;
        boolean checkc = true;
        input = new Scanner(System.in);
        System.out.print("Enter Account ID to search: ");
        while (true) {
            try {
                accountNumberS = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
        for (int i = 0; i < nextAccount; i++) {
            if (accountNumberS == Accounts[i].getAccountNumber()) {
                if (Accounts[i].getAccountBalance() < 100) {
                    System.out.println("Cannot withdraw balances less than $100.");
                    return;
                }
                while (checkc == true) {
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        withdraw = Double.parseDouble(input.nextLine());
                        checkc = false;
                        if (withdraw > Accounts[i].getAccountBalance()) {
                            double currentbal = Accounts[i].getAccountBalance();
                            checkc = true;
                            throw new InsufficientFundsException(currentbal);
                        } else if (withdraw < 100) {
                            System.out.println("\nCannot withdaw less than $100.\n");
                            System.out.println("Account Balance: " + Accounts[i].getAccountBalance() + "\n");
                            checkc = true;
                        }
                    } catch (InsufficientFundsException e) {
                        e.getMessage();
                    } catch (NumberFormatException e) {
                        System.out.println("\nError! Must be a number!\n");
                    }
                }
                Accounts[i].setAccountBalance(Accounts[i].getAccountBalance() - withdraw);
                displayAccountDetails(Accounts[i]);
                return;
            } else if (i == nextAccount - 1) {
                System.out.println("\nERROR! No such account in array.\n");
                return;
            }
        }
    }

    public void deposit() {
        if (nextAccount == 0) {
            System.out.println("\nArray is empty\n");
            return;
        }
        int accountNumberS;
        double deposit;
        input = new Scanner(System.in);
        System.out.print("Enter Account ID to search: ");
        while (true) {
            try {
                accountNumberS = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
        for (int i = 0; i < nextAccount; i++) {
            if (accountNumberS == Accounts[i].getAccountNumber()) {
                while (true) {
                    try {
                        System.out.print("Enter amount to deposit: ");
                        deposit = Double.parseDouble(input.nextLine());
                        if (deposit < 0) {
                            throw new Exception();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nError! Must be a number!");
                    } catch (Exception e) {
                        System.out.println("\nError! Must be a positive number.");
                    }
                }
                Accounts[i].setAccountBalance(Accounts[i].getAccountBalance() + deposit);
                displayAccountDetails(Accounts[i]);
            } else if (i == nextAccount - 1) {
                System.out.println("\nNo such account in array.\n");
                return;
            }
        }
    }
}