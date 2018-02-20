
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
	
	public String withdraw(CashCard card, double amount) {
		if (amount > this.maxWithdrawn) {
			return "This amount exceeds the maximum amount you can withdraw per transaction. Please enter the amount or quit.";
		}
		else if( this.bank.withdraw(card, amount)) {
			return amount + "is withdrawn from your account. The remaining balance in the account is . If you have more transactions, enter the amount or quit.";
		}
		else {
			return "The amount exceeds the current balance. Enter another amount or quit.";
		}
	}

}
