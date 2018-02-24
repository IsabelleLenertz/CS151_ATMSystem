/**
 * License info: this software was written and belongs to Isabelle Delmas. Ask authorization before use, no commercial use allowed. Contact info: isabelle@delmas.us
 */

import java.time.LocalDate;
/**
 * Represent a card use to access an account and withdraw money
 * @author Isabelle Delmas
 * Created: 2018-02-20
 *
 */
public class CashCard {
	
	private String bankAffiliation;
	private int accountAffiliation;
	private LocalDate expirationDate;
	private String password;
	
	/**
	 * Crate a card that expires three years from the creation date
	 * @param affiliatedBank bank the card is affiliated to
	 * @param affiliatedAccount account the card is affiliated to
	 * @param newPassword password associated with the card
	 */
	public CashCard(String affiliatedBank, int affiliatedAccount, String newPassword) {
		this.accountAffiliation = affiliatedAccount;
		this.bankAffiliation = affiliatedBank;
		final LocalDate today = LocalDate.now();
		this.expirationDate = today.plusYears(3);
		this.password = newPassword;
	}
	
	/**
	 * Verifies the password
	 * @param word word to compare with the password
	 * @return true if the password matches
	 */
	public boolean checkPassword(String word) {
		return this.password.equals(word);
	}
	
	/**
	 * Define the expiration date
	 * @param year year of the expiration date
	 * @param month month of the expiration date
	 * @param day day of the expiration date
	 */
	public void setExpirationDate(int year, int month, int day) {
		this.expirationDate = LocalDate.of(year, month, day);
	}
	/**
	 * Get the id of the card
	 * @return id = bankAffiliation + accountNumber
	 */
	public String getId() {
		return this.bankAffiliation + this.accountAffiliation;
	}
	
	/**
	 * Get the bank associated with the card
	 * @return bank associated with the card
	 */
	public String getBank() {
		return this.bankAffiliation;
	}
	
	/**
	 * Get the account associated with the card
	 * @return account associated with the card
	 */
	public int getAccountId() {
		return this.accountAffiliation;
	}
	
	/**
	 * Check if the card is expired
	 * @return true is the card is not expired
	 */
	public boolean isExpired() {
		return this.expirationDate.isBefore(LocalDate.now());
	}
	

	/**
	 * Return a string with all the information about the cash card
	 * @return string with all the information about the cash card
	 */
	@Override
	public String toString() {
		return "Cash Card (cardId: " + this.getId() + ", bankid: " + this.bankAffiliation + ", account number #: " + this.accountAffiliation + "), expires on " + this.expirationDate.toString() + ", password " + this.password;
	}
	

}
