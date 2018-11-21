package JavaException;

import java.util.Scanner;

public class BankTest {

	public static void main(String[] args) {
		BankTest oBankTest = new BankTest();
		Bank oBank = new Bank();
		Scanner input;
		int choice;
		while (true) {
			input = new Scanner(System.in);
			oBankTest.printMenu();
			choice = oBankTest.doChoice(input);
			switch (choice) {
			case 1:
				oBank.createAccount();
				break;
			case 2:
				oBank.withdraw();
				break;
			case 3:
				oBank.deposit();
				break;
			case 4:
				System.out.println("Exitting.");
				System.exit(0);
			default:
				System.out.println("\nError! Please enter a valid option (1-4)\n");
				break;
			}
		}
	}

	public void printMenu() {
		System.out.println("1. Add Account");
		System.out.println("2. Withdraw from");
		System.out.println("3. Deposit to");
		System.out.println("4. Exit\n");
		System.out.print("Option: ");
	}

	public int doChoice(Scanner input) {
		int choice;
		while (true) {
			try {
				choice = Integer.parseInt(input.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Please enter a number: ");
			}
		}
		return choice;
	}
}
