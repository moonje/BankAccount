package bankApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

/***********************************************************************
 * 
 * 
 * @author Jennifer Moon
 * @author Kelsey Brennan
 * @author Molly Alger
 * @version 10/30/2015
 **********************************************************************/
public class BankGUI extends JFrame {

	/** An instance of BankModel for the GUI **/
	private BankModel bank;

	/** JMenuItems for the top menu bar **/
	private JMenuItem textLoad;
	private JMenuItem textSave;
	private JMenuItem binaryLoad;
	private JMenuItem binarySave;
	private JMenuItem xmlLoad;
	private JMenuItem xmlSave;
	private JMenuItem quit;
	private JMenuItem numberSort;
	private JMenuItem nameSort;
	private JMenuItem dateSort;
	private JMenuItem checking;
	private JMenuItem savings;
	private JMenuItem update;
	private JMenuItem delete;

	/** JTextFields for gathering user input **/
	private JTextField accNum = new JTextField();
	private JTextField accOwn = new JTextField();
	private JTextField date = new JTextField();
	private JTextField accBalance = new JTextField();
	private JTextField fee = new JTextField();
	private JTextField interest = new JTextField();
	private JTextField minimum = new JTextField();

	/** The table displayed by the GUI **/
	JTable table; 

	/** Array of objects for dialog box **/
	private Object[] checkingMessage = 
		{"Account Number: ", accNum, "Account Owner: ",
				accOwn, "Date Opened: ", date,
				"Account Balance: ", accBalance,
				"Monthly Fee: ", fee
		};

	/** Array of objects for dialog box **/
	private Object[] savingsMessage = 
		{"Account Number: ", accNum, "Account Owner: ",
				accOwn, "Date Opened: ", date,
				"Account Balance: ", accBalance,
				"Interest: ", interest,
				"Minimum Balance: ", minimum
		};

	/******************************************************************
	 * Constructor creates the JFrame and the elements displayed 
	 * within the JFrame
	 *****************************************************************/
	public BankGUI() {

		super("Bank Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//call the method to create the menu bar
		setJMenuBar(createMenus());

		pack();
		setSize(675,625);

		bank = new BankModel();

		JPanel j = new JPanel();
		j.setLayout(new BorderLayout());

		//call the method to create the table
		j.add(createTable(), BorderLayout.NORTH);

		add(j);

		setVisible(true);

	}

	/******************************************************************
	 * Creates the JTable that displays user data
	 * 
	 * @return tables the table created to display account info
	 *****************************************************************/
	private JPanel createTable() {

		JPanel tables = new JPanel();

		table = new JTable(bank);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(650,600));
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		tables.add(scrollPane);

		return tables;
	}

	/******************************************************************
	 * Creates the menu bar for selecting files, creating and 
	 * updating accounts, and sorting the accounts
	 * 
	 * @return menuBar the top menubar in the JFrame
	 *****************************************************************/
	public JMenuBar createMenus() {

		JMenuBar menuBar;
		JMenu file, account, sort;

		// Create the menu bar
		menuBar = new JMenuBar();

		ButtonListener listen = new ButtonListener();

		// Build the first menu
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().
		setAccessibleDescription("Save, Load, Quit");
		menuBar.add(file);

		//create the menu items for File
		binaryLoad = new JMenuItem("Load from Binary...");
		binaryLoad.getAccessibleContext().
		setAccessibleDescription("Load from a binary file");
		binaryLoad.addActionListener(listen);
		file.add(binaryLoad);

		binarySave = new JMenuItem("Save to Binary...");
		binarySave.getAccessibleContext().
		setAccessibleDescription("Save to a binary file");
		binarySave.addActionListener(listen);
		file.add(binarySave);

		file.addSeparator();

		textLoad = new JMenuItem("Load from Text...");
		textLoad.getAccessibleContext().
		setAccessibleDescription("Load a binary file");
		textLoad.addActionListener(listen);
		file.add(textLoad);

		textSave = new JMenuItem("Save to Text...");
		textSave.getAccessibleContext().
		setAccessibleDescription("Save to a binary file");
		textSave.addActionListener(listen);
		file.add(textSave);

		file.addSeparator();

		xmlLoad = new JMenuItem("Load from XML...");
		xmlLoad.getAccessibleContext().
		setAccessibleDescription("Load a binary file");
		xmlLoad.addActionListener(listen);
		file.add(xmlLoad);

		xmlSave = new JMenuItem("Save to XML...");
		xmlSave.getAccessibleContext().
		setAccessibleDescription("Save to a binary file");
		xmlSave.addActionListener(listen);
		file.add(xmlSave);

		file.addSeparator();

		quit = new JMenuItem("Quit");
		quit.getAccessibleContext().
		setAccessibleDescription("Quit the program");
		quit.addActionListener(listen);
		file.add(quit);

		//Build the second menu
		account = new JMenu("Account");
		account.setMnemonic(KeyEvent.VK_A);
		account.getAccessibleContext().
		setAccessibleDescription("Account Options");
		menuBar.add(account);

		//create the menu items for Account
		checking = new JMenuItem("New Checking Account");
		checking.getAccessibleContext().
		setAccessibleDescription("Create a New Checking Account");
		checking.addActionListener(listen);
		account.add(checking);

		savings = new JMenuItem("New Savings Account");
		savings.getAccessibleContext().
		setAccessibleDescription("Create a New Savings Account");
		savings.addActionListener(listen);
		account.add(savings);

		update = new JMenuItem("Update Account");
		update.getAccessibleContext().
		setAccessibleDescription("Update account");
		update.addActionListener(listen);
		account.add(update);

		delete = new JMenuItem("Delete Account");
		delete.getAccessibleContext().
		setAccessibleDescription("Delete account");
		delete.addActionListener(listen);
		account.add(delete);

		//Build the third menu
		sort = new JMenu("Sort");
		sort.setMnemonic(KeyEvent.VK_S);
		sort.getAccessibleContext().
		setAccessibleDescription("Sort by various criteria");
		menuBar.add(sort);

		//create the menu items for Sort
		numberSort = new JMenuItem("By Account Number");
		numberSort.getAccessibleContext().
		setAccessibleDescription("Sort by Account Number");
		numberSort.addActionListener(listen);
		sort.add(numberSort);

		nameSort = new JMenuItem("By Account Owner");
		nameSort.getAccessibleContext().
		setAccessibleDescription("Sort by Account Owner");
		nameSort.addActionListener(listen);
		sort.add(nameSort);

		dateSort = new JMenuItem("By Date Opened");
		dateSort.getAccessibleContext().
		setAccessibleDescription("Sort by Date Opened");
		dateSort.addActionListener(listen);
		sort.add(dateSort);

		//Returns the built menu bar
		return menuBar;

	}

	/******************************************************************
	 * A buttonlistener to determine what actions to take when
	 * a user selects items from the drop down menus
	 *****************************************************************/
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == textLoad) {

		        //File Chooser to open in the current directory
		        JFileChooser fc = new JFileChooser();
		        File currentDir = new File(System.getProperty(
		        		"user.dir"));
		        fc.setCurrentDirectory(currentDir);

		        // display the file choose
		        int returnVal = fc.showOpenDialog(null);

		        // did the user select a file?
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            String filename = fc.getSelectedFile().getName();

		            //Use the provided filename to read customer data
					try {
						bank.loadText(filename);
					} catch (Exception exception){
						JOptionPane.showMessageDialog(null, 
								"Unable to load file.\n" +
								"Please confirm it is a text file.");
					}
		        }	
			}

			if (e.getSource() == textSave) {

				 //File Chooser to open in the current directory
		        JFileChooser fc = new JFileChooser();
		        File currentDir = new File(System.getProperty(
		        		"user.dir"));
		        fc.setCurrentDirectory(currentDir);

		        // display the file choose
		        int returnVal = fc.showSaveDialog(null);

		        // did the user select a file?
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            String filename = fc.getSelectedFile().getName();

		            //Use the provided filename to read customer data
					try{
						bank.saveText(filename);
					} catch (Exception exception){
						JOptionPane.showMessageDialog(null, 
								"Unable to save file.");
					}
		        }
			}

			if (e.getSource() == binaryLoad) {

		        //File Chooser to open in the current directory
		        JFileChooser fc = new JFileChooser();
		        File currentDir = new File(System.getProperty(
		        		"user.dir"));
		        fc.setCurrentDirectory(currentDir);

		        // display the file choose
		        int returnVal = fc.showOpenDialog(null);

		        // did the user select a file?
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            String filename = fc.getSelectedFile().getName();

		            //Use the provided filename to read customer data
					try{
						bank.loadBinary(filename);
					} catch (Exception exception){
						JOptionPane.showMessageDialog(null, 
								"Unable to load file. \n" +
								"Please confirm it is a binary file.");
					}
		        }	
			}

			if (e.getSource() == binarySave) {

				 //File Chooser to open in the current directory
		        JFileChooser fc = new JFileChooser();
		        File currentDir = new File(System.getProperty(
		        		"user.dir"));
		        fc.setCurrentDirectory(currentDir);

		        // display the file choose
		        int returnVal = fc.showSaveDialog(null);

		        // did the user select a file?
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            String filename = fc.getSelectedFile().getName();

		            //Use the provided filename to read customer data
					try {
						bank.saveBinary(filename);
					} catch (IOException exception){
						JOptionPane.showMessageDialog(null, 
								"Unable to save file.");
					}
		        }
			}
			
			if(e.getSource() == xmlLoad){
				
				//bank.loadXML(filename);
				
			}
			
			if(e.getSource() == xmlSave){
				
				//bank.saveXML(filename);
				
			}

			if (e.getSource() == quit) {
				System.exit(0);
			}

			//sort by account number
			if (e.getSource() == numberSort) {
				bank.sortByAccountNumber();
			}

			//sort by account owner's name
			if (e.getSource() == nameSort) {
				bank.sortByAccountName();
			}

			//sort by date account was opened
			if (e.getSource() == dateSort) {
				bank.sortByAccountDateOpened();
			}

			//create a new checking account
			if (e.getSource() == checking) {

				//CheckingAccount check = new CheckingAccount();
				//CheckingInputDialog c = new CheckingInputDialog(this, check);
				//bank.addAccount();

				int option = JOptionPane.showConfirmDialog(null, checkingMessage, 
						"Create a New Checking Account", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION){
					newChecking();
				}


			}

			//create a new savings account
			if (e.getSource() == savings) {

				//SavingsAccount save = new SavingsAccount();
				//SavingsInputDialog s = new SavingsInputDialog(save);

				int option = JOptionPane.showConfirmDialog(null, 
						savingsMessage, 
						"Create a New Savings Account", 
						JOptionPane.OK_CANCEL_OPTION);

				if (option == JOptionPane.OK_OPTION){
					newSavings();
				}	
			}

			//update an existing account
			if (e.getSource() == update) {
				int index = table.getSelectedRow();
				if (index >= 0) {
					Account account = bank.getAccountAt(index);

					if (account instanceof CheckingAccount){
						accNum.setText("" + account.getAccountNumber());
						accOwn.setText(account.getAccountOwner());
						date.setText(account.calendarToString(account.
								getDateOpened()));
						accBalance.setText("" +account.getAccountBalance());
						fee.setText("" + ((CheckingAccount) account).
								getMonthlyFee());

						int option = JOptionPane.showConfirmDialog(null, 
								checkingMessage, 
								"Create a New Checking Account", 
								JOptionPane.OK_CANCEL_OPTION);

						if (option == JOptionPane.OK_OPTION){
							newChecking();
							bank.deleteAccount(index);
						}

					} else if (account instanceof SavingsAccount){
						accNum.setText("" + account.getAccountNumber());
						accOwn.setText(account.getAccountOwner());
						date.setText(account.calendarToString(account.
								getDateOpened()));
						accBalance.setText("" +account.getAccountBalance());

						minimum.setText("" + (((SavingsAccount) account).
								getMinBalance()));
						interest.setText("" + (((SavingsAccount) account).
								getInterestRate()));

						int option = JOptionPane.showConfirmDialog(null, 
								savingsMessage, 
								"Create a New Savings Account", 
								JOptionPane.OK_CANCEL_OPTION);

						if (option == JOptionPane.OK_OPTION){
							newSavings();
							bank.deleteAccount(index);
						}	
					}
				}
			}

			//delete an account
			if (e.getSource() == delete) {
				// Ask user if they want to delete the account
				Object[] options = {"Yes", "No"};
				int reply = JOptionPane.showOptionDialog(null, "Delete"
						+ " account?", "Confirm delete",JOptionPane.
						DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[1]);

				// Delete account if user selects 'yes'
				if (reply == 0) {
					int index = table.getSelectedRow();
					bank.deleteAccount(index);
				}
			}
		}
	}

	/******************************************************************
	 * Checks the user's input for correct formatting and inputs and 
	 * creates a new checking account if all user inputs are 
	 * correctly formatted and with acceptable inputs. 
	 * 
	 * @throws IllegalArgumentException if incorrect user input
	 *****************************************************************/
	public void newChecking() throws IllegalArgumentException{
		try {
			//Gets the account number
			int acctNum = Integer.parseInt(accNum.getText());
			
			//Gets the date opened 
			String dateString = date.getText();
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
			
			//Gets the balance 
			double accBal = Double.parseDouble(accBalance.getText());
			
			//Make sure balance is not negative
			if (accBal < 0)
				throw new IllegalArgumentException();
			
			//Gets the monthly fee
			double accFee = Double.parseDouble(fee.getText());
			
			//Make sure fee is not negative
			if (accFee < 0)
				throw new IllegalArgumentException();
			
			//Creates a new account
			CheckingAccount cnew = new CheckingAccount(acctNum, 
					accOwn.getText(), greg, accBal, accFee);
			
			//Sends the account to the model 
			bank.newAccount(cnew);
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error in input!");
		}
	}
	/******************************************************************
	 * Checks the user's input for correct formatting and inputs and 
	 * creates a new savings account if all user inputs are 
	 * correctly formatted and with acceptable inputs. 
	 * 
	 * @throws IllegalArgumentException if incorrect user input
	 *****************************************************************/
	public void newSavings() throws IllegalArgumentException{
		try {
			//Gets the account number
			int acctNum = Integer.parseInt(accNum.getText());
			
			//Gets the date opened 
			String dateString = date.getText();
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
			
			//Gets the balance 
			double accBal = Double.parseDouble(accBalance.getText());
			
			//Make sure balance is not negative
			if (accBal < 0)
				throw new IllegalArgumentException();
			
			double minBal = 1;
			
			//Gets the minimum balance and makes sure it's not
			//greater than the current balance
			if(Double.parseDouble(minimum.getText()) <= accBal){
				minBal = Double.parseDouble(minimum.getText());
				
				//Make sure minimum balance is not negative
				if (minBal < 0)
					throw new IllegalArgumentException();
			}
			else
				throw new IllegalArgumentException();
	
			//Gets the interest rate 
			double intRate = Double.parseDouble(interest.getText());
			
			//Make sure interest rate is not negative
			if (intRate < 0)
				throw new IllegalArgumentException();
			
			//Creates a new account
			SavingsAccount snew = new SavingsAccount(acctNum, 
					accOwn.getText(), greg, accBal, minBal, intRate);
			
			//Sends the account to the model 
			bank.newAccount(snew);
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error in input!");
		}
	}

	/******************************************************************
	 * Main method to create the GUI
	 * 
	 * @param args array of Strings 
	 *****************************************************************/
	public static void main(String[] args) {
		BankGUI gui = new BankGUI();
	}
}