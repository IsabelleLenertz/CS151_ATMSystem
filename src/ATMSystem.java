import java.util.ArrayList;
import java.util.Scanner;

public class ATMSystem {
	public final static double INITIAL_BALANCE = 40.0;
	
	public static void main(String[] args) {
		// Create the Banks
		Bank bankA = new Bank("BankOfA");
		Bank bankB = new Bank("BankOfB");
		
		// Store all the CashCard generated by the account openings
		ArrayList<CashCard> listOfCardsBankA = new ArrayList<CashCard>();
		
		// Open accounts in bank A
		listOfCardsBankA.add(bankA.openAccount(INITIAL_BALANCE, "cat"));
		listOfCardsBankA.add(bankA.openAccount(INITIAL_BALANCE, "dog"));
		listOfCardsBankA.add(bankA.openAccount(INITIAL_BALANCE, "bird"));
		listOfCardsBankA.get(0).setExpirationDate(2016,  12,  12); // make sure at least one card is expired
		
		// Create two ATMs for bank A
		ATM firstATMA = new ATM(bankA, 150);
		ATM secondATMA = new ATM(bankA, 600);
		
		// Store all the CashCard generated by the account openings
		ArrayList<CashCard> listOfCardsBankB = new ArrayList<CashCard>();
		
		// Open accounts in bank B
		listOfCardsBankB.add(bankB.openAccount(INITIAL_BALANCE, "bunny"));
		listOfCardsBankB.add(bankB.openAccount(INITIAL_BALANCE, "parrot"));
		listOfCardsBankB.add(bankB.openAccount(INITIAL_BALANCE, "mouse"));
		listOfCardsBankB.get(0).setExpirationDate(2016,  12,  12); // make sure at least one card is expired
		
		// Create two ATMs for bank B
		ATM firstATMB = new ATM(bankA, 300);
		ATM secondATMB = new ATM(bankB, 900);
		
		// Display summary of Banks, accounts, ATMS, and CashCards available
		// Bank A
		System.out.println("States of two Banks  \n");
		System.out.println("Assume all accounts have $" + INITIAL_BALANCE + " preloaded.");
		System.out.println(bankA.getName() + "( " + bankA.getNumberOfCustomer() + " customers)");
		for(CashCard element : listOfCardsBankA) {
			System.out.println("Customer - " + element.toString());
		}
		// Bank B
		System.out.println("\n" + bankB.getName() + "( " + bankB.getNumberOfCustomer() + " customers)");
		for(CashCard element : listOfCardsBankB) {
			System.out.println("Customer - " + element.toString());
		}
		// Four ATMs
		System.out.println("\nATM1_A: (ATM1 from" + bankA.getName() + ") The maximum amount of cash a card can widthraw per day: $" + firstATMA.getMaxWithdrawn());
		System.out.println("ATM2_A: (ATM2 from" + bankA.getName() + ") The maximum amount of cash a card can widthraw per day: $" + secondATMA.getMaxWithdrawn());
		System.out.println("ATM1_B: (ATM1 from" + bankB.getName() + ") The maximum amount of cash a card can widthraw per day: $" + firstATMB.getMaxWithdrawn());
		System.out.println("ATM2_B: (ATM2 from" + bankB.getName() + ") The maximum amount of cash a card can widthraw per day: $" + secondATMB.getMaxWithdrawn());
		
		String userInput = "";
		Scanner in = new Scanner(System.in);
		ATM currentATM = null;
		CashCard currentCard = null;
		
		// Keeps going as long as the user does not enter quit when at the end of the transaction
		while(!userInput.toLowerCase().equals("quit")) {
			
			// Ask for an ATM and validate it exists
			do {
				System.out.println("Enter your choice of ATM");
				userInput = in.nextLine();
				if(userInput.toLowerCase().equals("atm1_a")) {
					currentATM = firstATMA;
				} else if(userInput.toLowerCase().equals("atm2_a")) {
					currentATM = secondATMA;
				} else if(userInput.toLowerCase().equals("atm1_b")) {
					currentATM = firstATMB;
				} else if (userInput.toLowerCase().equals("atm2_b")) {
					currentATM = secondATMB;
				} else {
					System.out.println("This ATM does not exit.");
				}
			} while(currentATM == null);
			
			// Ask for a card id that is valid, keep asking until a valid card id is given
			boolean validATM = false;
			boolean notExpired = false;
			do {
				// Ask for the card
				do {

					System.out.println("Enter your card");
					userInput = in.nextLine();
					// Check if the card in the array of cards from bank A
					for (CashCard element : listOfCardsBankA) {
						if (element.getId().toLowerCase().equals(userInput.toLowerCase())) {
							currentCard = element;
							break;
						}
					}
					// Check if the card is in the array of cards from bank B (only is was not found
					// yet)
					if (currentCard == null) {
						for (CashCard element : listOfCardsBankB) {
							if (element.getId().toLowerCase().equals(userInput.toLowerCase())) {
								currentCard = element;
								break;
							}
						}
					}
				} while (currentCard == null);
				
				// Check if the card is supported by the ATM
				validATM = currentATM.validateCardSuport(currentCard);
				if(!validATM) {
					System.out.println("This card is not supported by this ATM");
				} else {
					// Check if the card is not expired
					notExpired = currentATM.validateCardExpirationDate(currentCard);
					if(!notExpired) {
						System.out.println("This card is expired and returned to you");
					}
				}
			} while (!validATM & !notExpired);
			
			// Ask for the password
			do {
				System.out.println("The card is accepted. Please enter your password.");
				userInput = in.nextLine();
				if (!currentATM.validatePassword(currentCard, userInput)) {
					System.out.println("This is the wrong password. Enter your password.");
				}
			} while (!currentATM.validatePassword(currentCard, userInput));
			System.out.println("“Authorization is accepted. Start your transaction by entering the amount to withdraw.");

			// Try to withdraw money from using the card
			// Validate the amount is less than the maximum that can be withdrawn in one transaction.
			int amount = 0;
			do {
				if (in.hasNextInt()) {
					amount = in.nextInt();
				}
				in.nextLine();
				if (amount > currentATM.getMaxWithdrawn()) {
					System.out.println("This amount exceeds the maximum amount you can withdraw per transaction. Please enter the amount or quit.");
				}
			} while(amount > currentATM.getMaxWithdrawn());
			
			// Try to withdraw
			boolean success = false;
			do {
				success = currentATM.withdraw(currentCard, amount);
				if(success) {
					System.out.println(amount + "$ is withdrawn from your account. The remaining balance if this account is "+ currentATM.getAccountBalance(currentCard) +" $. If you have more transactions, enter the amount or quit. - Anything else will bring you back to the choice of ATM");
				} else {
					System.out.println("The amount exceeds the current balance. Enter another amount or quit.");
				}
				if(in.hasNextInt()) {
					amount = in.nextInt();
					success = false;	// does not exit the do/while loop to withdraw more money
					in.nextLine();
				} else {
					userInput = in.nextLine();
				}
			} while(!success && userInput.toLowerCase().equals("quit"));
			
			// Reinitialize the current card and ATM before starting the main loop again.
			currentATM = null;
			currentCard = null;
		}
		
		in.close();
	}

}
