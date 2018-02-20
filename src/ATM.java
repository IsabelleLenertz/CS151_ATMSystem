
public class ATM {
	private Bank bank;
	private int maxWithdrawn;
	
	/**
	 * Constructor
	 * @param affiliatedBank bank affiliated with the ATM
	 * @param max max amount that can be withdrawn at one time
	 */
	public ATM(Bank affiliatedBank, int max) {
		this.bank = affiliatedBank;
		this.maxWithdrawn = max;
	}
	
	
	public int getMaxWithdrawn() {
		return this.maxWithdrawn;
	}
	
	/**
	 * Validate that the ATM supports the card
	 * @param card card to check
	 * @return true if the ATM supports the card
	 */
	public boolean validateCardSuport(CashCard card) {
		return this.bank.getName().equals(card.getBank());
	}
	
	/**
	 * Validate that the card is not expired
	 * @param card card to check
	 * @return true if the card if not expired
	 */
	public boolean validateCardExpirationDate(CashCard card) {
		return !card.isExpired();
	}
	
	/**
	 * Validate the password id correct
	 * @param card card to check
	 * @param pasword password to check
	 * @return true is the password allows access to the account
	 */
	public boolean validatePassword(CashCard card, String password) {
		return bank.authorizeAccountAccess(card,  password);
	}
	
	/**
	 * Return the balance of the account linked to the card
	 * @param card card inserted in the ATM
	 * @return balance of the account
	 */
	public double getAccountBalance(CashCard card) {
		return this.bank.getAccountBalance(card);
		
	}
	
	/**
	 * Withdraw money from the account linked to the card and give it to the user
	 * @param card card in the ATM
	 * @param amount amount to withdraw
	 * @return true if the withdraw was successful (sufficient balance)
	 */
	public boolean withdraw(CashCard card, double amount) {
		return this.bank.withdraw(card, amount);
	}

}
