package bankApplication;

import java.io.Serializable;
import java.text.*;
import java.util.GregorianCalendar;

/***********************************************************************
 * Parent Account class used to make Checking & Savings Accounts 
 * 
 * @author Jennifer Moon
 * @author Kelsey Brennan
 * @author Molly Alger
 * @version 10/30/2015
 **********************************************************************/
public abstract class Account implements Serializable {

	/** Used for saving to binary **/
	private static final long serialVersionUID = 1L;

	/** Account Number **/
	protected int number; 

	/** Account Owner **/
	protected String owner;

	/** Date Account Was Opened **/
	protected GregorianCalendar dateOpened;

	/** Account Balance **/
	protected double balance; 
	
	/** Currency Formatting **/
	protected NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	/** Date Formatting **/
	protected SimpleDateFormat date = new SimpleDateFormat
			("MM/dd/yyyy");

	/*******************************************************************
	 * Constructor used to instantiate the account number, owner, 
	 * balance, and date opened. 
	 * 
	 * @param number, the account number
	 * @param owner, the account owner
	 * @param dateOpened, date account was opened
	 * @param balance, account balance 
	 ******************************************************************/
	public Account(int number, String owner, 
			GregorianCalendar dateOpened, double balance){
		
		this.number = number; 
		this.owner = owner;
		this.dateOpened = dateOpened; 
		this.balance = balance; 
	}

	/*******************************************************************
	 * Sets the account number to the specified integer 
	 * 
	 * @param newNumber, the new account number
	 ******************************************************************/
	public void setAccountNumber(int newNumber){
		number = newNumber; 
	}

	/*******************************************************************
	 * Gets the account number
	 * 
	 * @return the account number
	 ******************************************************************/
	abstract int getAccountNumber();

	/*******************************************************************
	 * Sets the account owner to the specified string
	 * 
	 * @param newOwner, the new account owner 
	 ******************************************************************/
	public void setAccountOwner(String newOwner){
		owner = newOwner; 
	}

	/*******************************************************************
	 * Gets the owner of the account
	 * 
	 * @return the account owner
	 ******************************************************************/
	abstract String getAccountOwner();

	/*******************************************************************
	 * Sets the date opened to the specified date
	 * 
	 * @param newDateOpened, the new date opened
	 ******************************************************************/
	public void setDateOpened(GregorianCalendar newDateOpened){
		dateOpened = newDateOpened; 
	}

	/*******************************************************************
	 * Gets the date in which the account was opened 
	 * 
	 * @return the date the account was opened 
	 ******************************************************************/
	abstract GregorianCalendar getDateOpened();

	/*******************************************************************
	 * Sets the account balance to the new balance 
	 * 
	 * @param newBalance, the new account balance
	 ******************************************************************/
	public void setAccountBalance(double newBalance){
		balance = newBalance; 
	}

	/*******************************************************************
	 * Gets the account's balance 
	 * 
	 * @return the account balance 
	 ******************************************************************/
	abstract double getAccountBalance();

	/*******************************************************************
	 * Checks to see if two accounts are equal 
	 * 
	 * @param other, the object being compared to
	 * @return whether or not the accounts are equal
	 ******************************************************************/
	public boolean equals(Object other){
		
		if (other instanceof Account) {
			Account acct = (Account) other;
			if (this.number == acct.number && 
					this.dateOpened.equals(acct.dateOpened) && 
					this.owner.equals(acct.owner) && 
					this.balance == acct.balance) {
				return true; 
			}
		}
		return false; 
	}

	/*******************************************************************
	 * Returns a string representing the information in account, in 
	 * order: account number, date opened, name, balance.  
	 * 
	 * @return string with account information 
	 ******************************************************************/
	public abstract String toString();
	
	/*******************************************************************
	 * Formats a Gregorian calendar as a string  
	 * 
	 * @return string with date
	 ******************************************************************/
	public String calendarToString(GregorianCalendar greg){
		return date.format(greg.getTime());
	}
}