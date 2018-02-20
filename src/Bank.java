import java.time.LocalDate;
import java.util.ArrayList;


public class Bank {
	ArrayList<Account> listOfAccounts = new ArrayList<>();
	String name;
	
	/**
	 * Create a Bank with a specific name
	 * @param newName name of the bank
	 */
	public Bank(String newName) {
		this.name = newName;
	}
	
	/**
	 * Get the name of the Bank
	 * @return the name of the bank
	 */
	public String getName() {
		return this.name;
	}
	
	public int getNumberOfCustomer() {
		return this.listOfAccounts.size();
	}
	/**
	 * Open a new account with a specified balance and password. the id is defined in the order the accounts were added.
	 * @param initialBalance money initially put in the new account
	 * @param initialPW password to access the account
	 * @return a cash card to access the account
	 */
	public CashCard openAccount(double initialBalance, String initialPW) {
		int id = this.listOfAccounts.size() + 1;
		this.listOfAccounts.add(new Account(initialBalance, id));
		CashCard card = new CashCard(this.name, this.listOfAccounts.get(this.listOfAccounts.size()-1).getAccountId(), initialPW);
		
		return card;
	}
	
	/**
	 * Check if the account can be accessed by the user
	 * @param card card to access the account
	 * @param password password entered by the user
	 * @return
	 */
	public boolean authorizeAccountAccess(CashCard card, String password) {
		return card.checkPassword(password);
	}
	
	/**
	 * Removes money from an account and updates the transaction log associated with the account
	 * Prerequisite: assumes access has been granted to the account using that card, else will throw errors.
	 * @param card the card used to withdraw the money
	 * @param amount the amount of money to withdraw
	 * @return true if the action was successful 
	 */
	public boolean withdraw(CashCard card, double amount) {
		// Removes money from the account, return true id successful
		if( this.listOfAccounts.get(card.getAccountId() - 1).withdraw(amount)) {
			String log = "Withdraw of " + amount + " by card with id " + card.getId()+ " on " + LocalDate.now().toString();
			this.listOfAccounts.get(card.getAccountId() -1).addToTransactionLog(log);
			return true;
		}
		String log = "Attempted withDraw of " + amount + " by card with id " + card.getId()+ " on " + LocalDate.now().toString() + "/nTransaction refused";
		this.listOfAccounts.get(card.getAccountId() -1).addToTransactionLog(log);
		return false;
	}
	
	
}
