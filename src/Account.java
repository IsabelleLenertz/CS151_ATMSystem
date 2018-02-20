import java.util.ArrayList;

public class Account {
	
	private Double balance;
	private int	accountId;
	private ArrayList<String> transactionLog = new ArrayList<String>();

	/**
	 * Constructor
	 * @param initialBalance initial balance of the account
	 * @param initialPW initial password of the account
	 */
	public Account(double initialBalance, int id) {
		this.balance = initialBalance;
		this.accountId = id;
	}
	
	/**
	 * Get the current balance of the account
	 * @return current balance
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Updates the transaction log associated to the account
	 * @param item message to add to the log
	 */
	public void addToTransactionLog(String item) {
		this.transactionLog.add(item);
	}
	
	/**
	 * Remove money from the account
	 * @param amount amount to remove
	 * @return true if the operation was successful, false is there are insufficient funds
	 */
	public boolean withdraw(double amount) {
		if(this.balance >= amount) {
			this.balance -= amount;
			return true;
		}
		return false;
	}
	
	/**
	 * Get the id of the account
	 * @return the id of the account that was given to it by the bank at creation time
	 */
	public int getAccountId() {
		return this.accountId;
	}
}
