package bankApplication;

import java.util.GregorianCalendar;
import java.text.NumberFormat;

/**********************************************************************
 * Child class to Account used to create a Savings Account. 
 * 
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/30/2015
 *********************************************************************/
public class SavingsAccount extends Account {
	
	/** Minimum balance required for a new savings account */
	private double minBalance; 
	
	/** Interest rate for a savings account */
	private double interestRate;
	
	/******************************************************************
	 * Default constructor 
	 *****************************************************************/
	public SavingsAccount(){
		
		super(0, null, null, 0);
		minBalance = 0;
		interestRate = 0;
	}
	
	/******************************************************************
	 * Constructor used to instantiate a new savings account with an
	 * account number, the owner name, the date it was opened, the 
	 * initial balance, the minimum balance, and the interest rate.
	 * 
	 * @param number the account number
	 * @param owner the account owner
	 * @param dateOpened date account was opened
	 * @param balance account balance 
	 * @param minBalance minimum account balance
	 * @param interestRate interest rate of account
	 *****************************************************************/
	public SavingsAccount(int number, String owner, GregorianCalendar 
			dateOpened, double balance, double minBalance, double 
			interestRate) {

		super(number, owner, dateOpened, balance);
		this.minBalance = minBalance;
		this.interestRate = interestRate;
	}
	
	/******************************************************************
	 * Returns the minimum balance of the savings account.
	 * 
	 * @return minimum balance of account
	 *****************************************************************/
	public double getMinBalance() {
		return minBalance;
	}
	
	/******************************************************************
	 * Sets the minimum balance for a savings account to the given
	 * value.
	 * 
	 * @param minBalance the minimum balance for the savings account
	 *****************************************************************/
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	
	/******************************************************************
	 * Returns the interest rate for the savings account.
	 * 
	 * @return the interest rate for the savings account
	 *****************************************************************/
	public double getInterestRate() {
		return interestRate;
	}
	
	/******************************************************************
	 * Sets the interest rate for a savings account to the given value.
	 * 
	 * @param interestRate the interest rate for the savings account
	 *****************************************************************/
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	/******************************************************************
	 * Checks if one savings account is equal to another savings 
	 * account.
	 * 
	 * @param other the other savings account that's being compared to
	 * the original one
	 * @return true if accounts are equal, false if they are not or if
	 * the parameter is not of type SavingsAccount
	 *****************************************************************/
	public boolean equals(Object other) {
		
		if (other instanceof SavingsAccount) {
			SavingsAccount otherAccount = (SavingsAccount) other;
			if (super.equals(otherAccount)) {
				if (this.minBalance == otherAccount.minBalance &&
						this.interestRate == otherAccount.interestRate)
					return true;
			}
		}
		return false; 
	}
		
	/******************************************************************
	 * Returns a string representing the information in account, in 
	 * order: account number, date opened, name, balance, and monthly
	 * fee.  
	 * 
	 * @return string with account information 
	 *****************************************************************/
	public String toString() {
		
		date.setCalendar(dateOpened);
		String strGreg = date.format(dateOpened.getTime());
		
		return "" + number + "; " + owner + "; " + strGreg + "; " +
			fmt.format(balance) + "; " + fmt.format(minBalance) + 
			"; " + interestRate;
	}

	/*******************************************************************
	 * Gets the account number
	 * 
	 * @return the account number
	 ******************************************************************/
	@Override
	public int getAccountNumber() {
		return number; 
	}
	
	/*******************************************************************
	 * Gets the owner of the account
	 * 
	 * @return the account owner
	 ******************************************************************/
	@Override
	public String getAccountOwner() {
		return owner; 
	}

	/*******************************************************************
	 * Gets the date in which the account was opened 
	 * 
	 * @return the date the account was opened 
	 ******************************************************************/
	@Override
	public GregorianCalendar getDateOpened() {
		return dateOpened; 
	}
	
	/*******************************************************************
	 * Gets the account's balance 
	 * 
	 * @return the account balance 
	 ******************************************************************/
	@Override
	public double getAccountBalance() {
		return balance; 
	}
}