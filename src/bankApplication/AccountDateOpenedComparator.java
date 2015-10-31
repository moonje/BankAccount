package bankApplication;

import java.util.Comparator;

/**********************************************************************
 * Class used to sort accounts by date opened 
 * 
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/30/2015
 *********************************************************************/
public class AccountDateOpenedComparator implements Comparator<Account>{

	
	/******************************************************************
	 * compares two accounts
	 * 
	 * @return if the first account is before or after the second 
	 * @param a1, the first account
	 * @param a2, the second account
	 *****************************************************************/
	@Override
	public int compare(Account a1, Account a2) {
		return a1.getDateOpened().compareTo(a2.getDateOpened());
	}

}
