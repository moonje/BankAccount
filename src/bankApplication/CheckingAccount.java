package bankApplication;

import java.util.GregorianCalendar;
import java.text.NumberFormat;

/**********************************************************************
 * Child class to Account used to make Checking Accounts
 * 
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/30/2015
 *********************************************************************/
public class CheckingAccount extends Account {

	/** The Monthly Fee */
	private double monthlyFee;

	
	/******************************************************************
	 * Default Constructor 
	 *****************************************************************/
	public CheckingAccount(){
		
		super(0, null, null, 0);
		monthlyFee = 0;
	}
	
	/******************************************************************
	 * Constructor used to instantiate a new checking account with an
	 * account number, the owner name, the date it was opened, the 
	 * initial balance, and the monthly account fee.
	 * 
	 * @param number the account number
	 * @param owner the account owner
	 * @param dateOpened date account was opened
	 * @param balance account balance 
	 * @param monthlyFee monthly fee to maintain account
	 *****************************************************************/
	public CheckingAccount(int number, String owner, GregorianCalendar 
			dateOpened, double balance, double monthlyFee) {
		
		super(number, owner, dateOpened, balance);
		this.monthlyFee = monthlyFee;
	}

	/******************************************************************
	 * Returns the monthly fee for the checking account.
	 * 
	 * @return the monthly account fee
	 *****************************************************************/
	public double getMonthlyFee() {
		return monthlyFee;
	}

	/******************************************************************
	 * Sets the monthly fee for a checking account to the given fee.
	 * 
	 * @param fee the new monthly fee to be assigned to a checking 
	 * account
	 *****************************************************************/
	public void setMonthlyFee(double fee) {
		monthlyFee = fee;
	}

	/******************************************************************
	 * Checks if one checking account is equal to another checking 
	 * account.
	 * 
	 * @param other the other checking account that's being compared to
	 * the original one
	 * @return true if accounts are equal, false if they are not or if
	 * the parameter is not of type CheckingAccount
	 *****************************************************************/
	public boolean equals(Object other) {
		if (other instanceof CheckingAccount) {
			CheckingAccount otherAccount = (CheckingAccount) other;
			if (super.equals(otherAccount)) {
				if (this.monthlyFee == otherAccount.monthlyFee)
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
			fmt.format(balance) + "; " + fmt.format(monthlyFee);
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