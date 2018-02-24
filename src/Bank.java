/**
 * License info: this software was written and belongs to Isabelle Delmas. Ask authorization before use, no commercial use allowed. Contact info: isabelle@delmas.us
 */

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represent a bank that holds bank accounts
 * @author Isabelle Delmas
 * Created: 2018-02-20
 * Updated: 2018-02-23		Reason: does not allow withdrawal of negative amount of money
 *
 */
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
	
	/**
	 * Get the number of accounts open in the bank
	 * @return number of accounts open in the bank
	 */
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
		CashCard card = new CashCard(this.name, id, initialPW);
		
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
		// Cannot withdraw a negative amount
		if(amount < 0) { return false;}
		
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
	
	/**
	 * Look up an account linked to a cash card and return the balance of that account
	 * @param card card to look up
	 * @return
	 */
	public double getAccountBalance(CashCard card) {
		return this.listOfAccounts.get(card.getAccountId()-1).getBalance();
	}
	
}
