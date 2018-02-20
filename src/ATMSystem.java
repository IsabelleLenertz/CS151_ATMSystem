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
				if(userInput.toLowerCase().equals("atm_1a")) {
					currentATM = firstATMA;
				} else if(userInput.toLowerCase().equals("atm_2a")) {
					currentATM = secondATMA;
				} else if(userInput.toLowerCase().equals("atm_1b")) {
					currentATM = firstATMB;
				} else if (userInput.toLowerCase().equals("atm_2b")) {
					currentATM = secondATMB;
				} else {
					System.out.println("This ATM does not exit.");
				}
			} while(currentATM == null);
			
			// Ask for a card id
			do {
				System.out.println("Enter your card");
				userInput = in.nextLine();
				// Check if the card in the array of cards from bank A
				for(CashCard element : listOfCardsBankA) {
					if(element.getId().toLowerCase().equals(userInput.toLowerCase())) {
						currentCard = element;
						break;
					}
				}
				// Check if the card is in the array of cards from bank B (only is was not found yet)
				if (currentCard == null) {
					for(CashCard element : listOfCardsBankB) {
						if(element.getId().toLowerCase().equals(userInput.toLowerCase())) {
							currentCard = element;
							break;
						}
					}
				}
			} while(currentCard == null);

			
			
			
			
			
			
			
			
			
			
			currentATM = null;
			currentCard = null;
		}
		
		in.close();
	}

}