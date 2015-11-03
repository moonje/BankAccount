package bankApplication;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

/**********************************************************************
 * Bank model used to hold bank accounts and save and load accounts. 
 *  
 * @author Kelsey Brennan
 * @author Jennifer Moon
 * @author Molly Alger
 * @version 10/30/2015
 *********************************************************************/
public class BankModel extends AbstractTableModel {

	/** ArrayList of Accounts **/
	private ArrayList<Account> acts; 

	/** Names of the Columns **/
	private String[] colNames = {"Account Number", "Account Owner", 
			"Date Opened", "Account Balance", "Account Info"};	

	/*******************************************************************
	 * Constructor
	 ******************************************************************/
	public BankModel(){
		acts = new ArrayList<Account>();
	}

	/*******************************************************************
	 * Creates a new account
	 * 
	 * @param acct, an Account
	 ******************************************************************/
	public void newAccount(Account acct){
		acts.add(acct);

		this.fireTableDataChanged();
	}

	/******************************************************************
	 * Removes the given account from the ArrayList.
	 * 
	 * @param account the index of the Account object to be removed 
	 * from the ArrayList
	 *****************************************************************/
	public void deleteAccount(int account) {
		acts.remove(account);
		this.fireTableDataChanged();
	}

	/******************************************************************
	 * Returns the number of columns 
	 * 
	 * @return the number of columns 
	 *****************************************************************/
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	/******************************************************************
	 * Returns the the number of rows 
	 * 
	 * @return the number of rows 
	 *****************************************************************/
	@Override
	public int getRowCount() {
		return acts.size();
	}

	/******************************************************************
	 * Returns the object at a specified position 
	 * 
	 * @param the column the object is at
	 * @param the row the column is at 
	 *****************************************************************/
	@Override
	public Object getValueAt(int row, int column) {

		switch(column){

		case 0: 
			return "" + acts.get(row).getAccountNumber();

		case 1:
			return acts.get(row).calendarToString(acts.get(row).
					getDateOpened());

		case 2:
			return acts.get(row).getAccountOwner();

		case 3:
			NumberFormat fmt = NumberFormat.getCurrencyInstance();
			return fmt.format(acts.get(row).getAccountBalance());

		case 4:
			NumberFormat f = NumberFormat.getCurrencyInstance();

			if(acts.get(row) instanceof CheckingAccount){

				return "Monthly Fee: " + 
						f.format(((CheckingAccount) acts.get(row)).
								getMonthlyFee());

			}
			else{
				return "Interest: " + (((SavingsAccount)acts.get(row)).
						getInterestRate()) + "%" +"  Minimum Balance: " 
						+ f.format(((SavingsAccount)acts.get(row)).
								getMinBalance());
			}
		}
		return null;
	}

	/******************************************************************
	 * Returns an account in the arraylist
	 * 
	 * @param index, the index of the account
	 *****************************************************************/
	public Account getAccountAt(int index){
		return acts.get(index);
	}

	/******************************************************************
	 *
	 *@return String of the column name
	 *@param column the desired column
	 *****************************************************************/
	@Override
	public String getColumnName(int column){
		String[] columnNames = { "Number", "Date Opened", 
				"Account Owner", "Current Balance", "Account Info" };
		return columnNames[column];
	}


	/******************************************************************
	 * Sorts the accounts in increasing order by the account number.
	 *****************************************************************/
	public void sortByAccountNumber() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountNumberComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}

	/******************************************************************
	 * Sorts the accounts in alphabetical order by the account owner's
	 * name.
	 *****************************************************************/
	public void sortByAccountName() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountOwnerComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}

	/******************************************************************
	 * Sorts the accounts by the date the account was opened.
	 *****************************************************************/
	public void sortByAccountDateOpened() {
		if (acts.size() > 1) {
			Collections.sort(acts, new AccountDateOpenedComparator());
			fireTableRowsUpdated(0, acts.size()-1);
		}
	}

	/******************************************************************
	 * Saves the array list of accounts to a serialized binary file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			binary file
	 * @throws IOException if unable to save file 
	 *****************************************************************/
	public void saveBinary(String filename) throws IOException{

		try{
			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.writeObject(acts);
			os.close();
		}
		catch(IOException e){
			throw new IOException(); 
		}
	}

	/******************************************************************
	 * Loads the array list of accounts from a serialized binary file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			binary file
	 * @throws Exception if unable to load the file 
	 *****************************************************************/
	public void loadBinary(String filename) throws Exception{
		if (filename.substring(filename.lastIndexOf(".") + 1).equals(
				"bin")) {
			try{
				FileInputStream fi = new FileInputStream(filename);
				ObjectInputStream is = new ObjectInputStream(fi);
				acts = (ArrayList<Account>)is.readObject();
				fireTableRowsUpdated(0, acts.size()-1);
				is.close();
			}
			catch(IOException e){

				throw new IOException(); 

			} catch (ClassNotFoundException e) {

				throw new ClassNotFoundException();
			}
		}
		else
			throw new IOException();

	}

	/******************************************************************
	 * Saves the array list of accounts to a text file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			text file
	 * @throws IOException if unable to save file 
	 *****************************************************************/
	public void saveText(String filename) throws Exception{

		PrintWriter out = null;
		try {

			//open and read file
			out = new PrintWriter(new BufferedWriter(new FileWriter
					(filename)));

			//print to file
			for (int i = 0; i < acts.size(); i++){

				Account account = acts.get(i);

				if (account instanceof CheckingAccount){

					CheckingAccount cacct = (CheckingAccount) account; 

					out.print("Checking," + 
							cacct.getAccountNumber() + "," + 
							cacct.getAccountOwner() + "," + 
							cacct.calendarToString(cacct.
									getDateOpened()) + 
							","  + cacct.getAccountBalance() + "," +
							cacct.getMonthlyFee() + "\n");

				} else if (account instanceof SavingsAccount){

					SavingsAccount sacct = (SavingsAccount) account; 

					out.print("Savings," + 
							sacct.getAccountNumber() + "," + 
							sacct.getAccountOwner() + "," + 
							sacct.calendarToString(sacct.
									getDateOpened()) + 
							","  + sacct.getAccountBalance() + "," +
							sacct.getMinBalance() + "," + 
							sacct.getInterestRate() + "\n");
				}

			}

		} catch (IOException e) {
			throw new IOException(); 

		} finally {
			//close file
			out.close();
		}	
	}

	/******************************************************************
	 * Loads the array list of accounts from a text file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			text file
	 * @throws Exception if unable to load the file 
	 *****************************************************************/
	public void loadText(String filename) throws Exception{
		if (filename.substring(filename.lastIndexOf(".") + 1).equals(
				"txt")) {

			try {
				// open the data file
				Scanner fileReader = new Scanner(new File(filename));

				acts.clear();

				//While there is a next line
				while (fileReader.hasNextLine()) {

					String text = fileReader.nextLine();

					String [] part = text.split(",");

					if (part[0].equals("Checking")){

						int accountNumber = Integer.parseInt(part[1]);

						//part[2] is the account owner 

						String [] date = part[3].split("/");
						int month = Integer.parseInt(date[0]);
						int day = Integer.parseInt(date[1]);
						int year = Integer.parseInt(date[2]);
						GregorianCalendar greg = new GregorianCalendar(
								year, month - 1, day);

						double balance = Double.parseDouble(part[4]);

						double fee = Double.parseDouble(part[5]);

						CheckingAccount check = new CheckingAccount(
								accountNumber, part[2], greg, balance, 
								fee);

						newAccount(check);

					} else if (part[0].equals("Savings")){

						int accountNumber = Integer.parseInt(part[1]);

						//part[2] is the account owner 

						String [] date = part[3].split("/");
						int month = Integer.parseInt(date[0]);
						int day = Integer.parseInt(date[1]);
						int year = Integer.parseInt(date[2]);
						GregorianCalendar greg = new GregorianCalendar(
								year, month - 1, day);

						double balance = Double.parseDouble(part[4]);

						double minBal = Double.parseDouble(part[5]);

						double interest = Double.parseDouble(part[6]);

						SavingsAccount save = new SavingsAccount(
								accountNumber, part[2], greg, balance,
								minBal, interest);

						newAccount(save);
					}
				}

				//close file
				fileReader.close();
			}

			// could not find file
			catch (FileNotFoundException error) {
				throw new FileNotFoundException();
			}

			// problem reading the file
			catch (IOException error) {
				throw new IOException();
			}

			catch (Exception error){
				throw new Exception();
			}
		}
		else
			throw new IOException();
	}

	/******************************************************************
	 * Saves the array list of accounts to an XML file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			xml file
	 *****************************************************************/
	public void saveXML(String filename){

		PrintWriter out = null;

		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					filename)));
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\"?>\n");
			sb.append("<accounts>\n");
			for (int i = 0; i < acts.size(); i++){

				Account account = acts.get(i);
				sb.append("<account>\n");

				if (account instanceof CheckingAccount){

					CheckingAccount cacct = (CheckingAccount) account;

					sb.append("<type>Checking</type>\n");
					sb.append("<account_number>" + 
							cacct.getAccountNumber()+
							"</account_number>\n");
					sb.append("<account_owner>" + 
							cacct.getAccountOwner()+
							"</account_owner>\n");
					sb.append("<date_opened>" + 
							cacct.calendarToString(cacct.
									getDateOpened())+
							"</date_opened>\n");
					sb.append("<balance>" + 
							cacct.getAccountBalance()+
							"</balance>\n");
					sb.append("<monthly_fee>" + 
							cacct.getMonthlyFee()+
							"</monthly_fee>\n");

				}

				if (account instanceof SavingsAccount){

					SavingsAccount sacct = (SavingsAccount) account;
					sb.append("<type>Savings</type>\n");
					sb.append("<account_number>" + 
							sacct.getAccountNumber()+
							"</account_number>\n");
					sb.append("<account_owner>" + 
							sacct.getAccountOwner()+
							"</account_owner>\n");
					sb.append("<date_opened>" + 
							sacct.calendarToString(sacct.
									getDateOpened())+
							"</date_opened>\n");
					sb.append("<balance>" + 
							sacct.getAccountBalance()+
							"</balance>\n");
					sb.append("<minimum_balance>" + 
							sacct.getMinBalance()+
							"</minimum_balance>\n");
					sb.append("<interest>" + 
							sacct.getInterestRate()+
							"</interest>\n");

				}
				sb.append("</account>\n");
			}
			sb.append("</accounts>\n");

			out.println(sb);

		}

		catch(Exception e){ 
			e.printStackTrace();

		} finally {
			out.close();
		}	
	}

	/******************************************************************
	 * Loads the array list of accounts from an xml file.
	 * 
	 * @param filename a String containing the file name of the 
	 * 			xml file
	 *****************************************************************/
	public void loadXML(String filename) throws Exception {
		
		if (filename.substring(filename.lastIndexOf(".") + 1).
				equals("xml")) {
			
			try{
				acts.clear();
				
				File file = new File(filename);
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.
						newInstance();
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				Document doc = db.parse(file);
				
				doc.getDocumentElement().normalize();

				NodeList actList = doc.getElementsByTagName("account");

				for(int i = 0; i < actList.getLength(); i++){

					Node actNode = actList.item(i);

					if(actNode.getNodeType() == Node.ELEMENT_NODE){

						Element element = (Element) actNode;
						
						String type = element.getElementsByTagName(
								"type").item(0).getFirstChild().
								getNodeValue();
						
						if(type.equals("Checking")){
							
							CheckingAccount check = new CheckingAccount
									();
							check.setAccountNumber(Integer.parseInt(
									element.getElementsByTagName(
											"account_number").
									item(0).getTextContent()));
							check.setAccountOwner(element.
									getElementsByTagName(
											"account_owner").
									item(0).getTextContent());
							check.setDateOpened((convertToGreg(element.
									getElementsByTagName("date_opened").
									item(0).getTextContent())));
							check.setAccountBalance(Double.parseDouble(
									(element.getElementsByTagName(
											"balance").item(0).
											getTextContent())));
							check.setMonthlyFee((Double.parseDouble(
									(element.getElementsByTagName(
											"monthly_fee").item(0).
											getTextContent()))));
							acts.add(check);
							
						}else{

							SavingsAccount save = new SavingsAccount();
							save.setAccountNumber(Integer.parseInt(
									element.getElementsByTagName(
											"account_number").
									item(0).getTextContent()));
							save.setAccountOwner(element.
									getElementsByTagName(
											"account_owner").
									item(0).getTextContent());
							save.setDateOpened((convertToGreg(element.
									getElementsByTagName("date_opened").
									item(0).getTextContent())));
							save.setAccountBalance(Double.parseDouble(
									(element.getElementsByTagName(
											"balance").item(0).
											getTextContent())));
							save.setMinBalance(Double.parseDouble(
									(element.getElementsByTagName(
											"minimum_balance").
									item(0).getTextContent())));
							save.setInterestRate(Double.parseDouble(
									(element.getElementsByTagName(
											"interest").item(0).
											getTextContent())));
							acts.add(save);
						}

					}
				}
				this.fireTableDataChanged();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		} else {
			throw new IOException();
		}
	}

	/******************************************************************
	 * Converts a string to a Gregorian Calendar 
	 * 
	 * @param date, string of the date 
	 * @throws IllegalArgumentException if not a valid date
	 *****************************************************************/
	private GregorianCalendar convertToGreg(String date) throws 
	IllegalArgumentException{

		//Gets the date opened 
		String dateString = date;
		String [] part = dateString.split("/"); 

		//Declare date variables
		int month; 
		int day; 
		int year; 
		GregorianCalendar greg; 

		//set date variables
		month = Integer.parseInt(part[0]);
		day = Integer.parseInt(part[1]); 
		year = Integer.parseInt(part[2]);

		//create calendar and set lenient mode
		greg = new GregorianCalendar();
		greg.setLenient(false);

		//check if values entered are valid
		try {
			greg.set(year, month - 1, day);
			greg.getTime();
		}

		//throw an error and set default date if entered date
		//is invalid
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}

		return greg;	
	}
}