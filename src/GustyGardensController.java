
//import the classes needed for this program
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * 
 * @author Shawn Wei
 * @version January 23, 2022
 * @course ICS4U1
 * @description This controller class connects the view and the model to work together
 */
public class GustyGardensController implements ActionListener, ItemListener, ListSelectionListener {
	
	//callling the classes
	private GustyGardensView view;
	private GustyGardensModel model;

	// Declare all the strings that will need to be used
	private String strCreditCardNumber, strFullName;

	private int intInvoiceNumber;

	// Declare and initialize the file name
	private String fileName = "";
	
	//arrays used to store the information
	private String[] vegetableTypeArray = new String[] { "", "", "", "", "", "" };
	private int[] vegetableQuantityArray = new int[] { 0, 0, 0, 0, 0, 0 };

	// constructor for the GustyGardensController class
	public GustyGardensController(GustyGardensModel model, GustyGardensView view) {
		this.model = model;
		this.view = view;
		view.addListener(this);
	}

	// handle button event
	public void actionPerformed(ActionEvent event) {
		String button = event.getActionCommand();
		view.show(button);
		// If the purchase button is pressed
		if (button == ("PURCHASE")) {

			// if no produce is selected (quantity)
			if (vegetableQuantityArray[0] == 0 && vegetableQuantityArray[1] == 0 && vegetableQuantityArray[2] == 0
					&& vegetableQuantityArray[3] == 0 && vegetableQuantityArray[4] == 0
					&& vegetableQuantityArray[5] == 0) {
				// add a reminder text
				view.setReminder(0);

			} else if (view.getName().equals("") || view.getCreditCard().equals("")) {
				// prompt/remind the user what they should enter
				view.setReminder(1);

			} else {
				view.setReminder(2);
				// get the credit card number and the name from the entered text fields
				strFullName = view.getName();
				strCreditCardNumber = view.getCreditCard();

				intInvoiceNumber = new Random().nextInt(9000) + 1000; // generate a random 4 digit number for the invoice number

				// create a file containing the encrypted order
				try {
					boolean reverseOption = model.getBlnReverseOption();
					boolean factorOption = model.getBlnFactorOption();
					// set the file name
					fileName = "OrderNumber" + intInvoiceNumber + ".txt";
					// create the file
					Formatter fileOutput = new Formatter(new File("Files/" + fileName));

					// input the additional encryption options into the file which allows for
					// seamless decryption
					if (reverseOption == true) {
						fileOutput.format("%s\n", 1);
					} else {
						fileOutput.format("%s\n", 0);
					}
					if (factorOption == true) {
						fileOutput.format("%s\n", 1);
					} else {
						fileOutput.format("%s\n", 0);
					}
					// add the name and full name into the encrypted file
					String strEncryptedFullName = model.Encryption(strFullName);
					fileOutput.format("%s\n", strEncryptedFullName);
					String strEncryptedCreditCard = model.Encryption(strCreditCardNumber);
					fileOutput.format("%s\n", strEncryptedCreditCard);

					// loop for each produce and input each produce into the encrypted file
					for (int index = 0; index < vegetableTypeArray.length; index++) {
						// add the quantity and the vegetable type
						String aline = vegetableQuantityArray[index] + " " + vegetableTypeArray[index];
						// encrypt the order
						String encryptedLine = model.Encryption(aline);
						// store it into the file
						fileOutput.format("%s\n", encryptedLine);
					}
					fileOutput.close();

					// exception handling for the file
				} catch (IOException ioException) {
					// Output a message to the user if an error occurs
					JOptionPane.showMessageDialog(null, "Error with file output", "Try again!",
							JOptionPane.INFORMATION_MESSAGE);
					System.exit(1);
				}

				// Store the order into another file so that it can keep track of the orders
				try {
					// get the existing file called Server.txt
					String strServer = "Files/Server.txt";
					FileWriter fileWriter = new FileWriter(strServer, true);
					fileWriter.append("\n" + fileName);// appends the string to the file
					fileWriter.close(); //close the file
				} catch (IOException ioe) {
					// exception handling for the file writer
					System.err.println("IOException: " + ioe.getMessage());
				}
				// informing the user that their purchase was successful
				JOptionPane.showMessageDialog(null, "Invoice #" + intInvoiceNumber, "Purchase Succesful!",
						JOptionPane.INFORMATION_MESSAGE);
				// clear the customer panel of the previous order
				view.clear();
			}
		}

	} // end method actionPerformed

	public void itemStateChanged(ItemEvent event) {
		// handle JComboBox event
		// determine whether the check box selected
		if (event.getStateChange() == ItemEvent.SELECTED) {
			vegetableTypeArray = view.getProduce();

			vegetableQuantityArray = view.getQuantity();
		}
		
		//check if the user would like to select the additional reverse option
		model.setBlnReverseOption(view.reverseChecked());

		//check if the user would like to select the additional reverse option
		model.setBlnFactorOption(view.factorChecked());



	}

	public void valueChanged(ListSelectionEvent event) {
		// handle JList events
		try {
			String fileName = view.getFileName();
			// read the order from the file
			BufferedReader input = new BufferedReader(new FileReader("Files/" + fileName));
			String aLine = input.readLine().trim();
			String display = "";

			// display the title of the order
			display = display + "\t" + fileName.substring(0, fileName.length() - 4);

			// These if statements will check for the security decryption method needed to
			// access the file
			if (aLine.trim().equals("1")) {
				model.setBlnReverseOption(true);
			} else {
				model.setBlnReverseOption(false);
			}
			aLine = input.readLine();
			if (aLine.trim().equals("1")) {
				model.setBlnFactorOption(true);
			} else {
				model.setBlnFactorOption(false);
			}

			aLine = input.readLine();
			// input the encrypted full name
			display = display + "\n" + "Full Name: " + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted credit card
			display = display + "\n" + "Credit Card Number: " + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted tomato order
			display = display + "\n\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted cucumber order
			display = display + "\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted eggplant order
			display = display + "\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted melon order
			display = display + "\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted beans order
			display = display + "\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();
			// input the encrypted mushrooms order
			display = display + "\n" + model.Decryption(aLine.trim());
			aLine = input.readLine();

			// display this order into the server panel
			view.showOrder(display);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("Exception");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("Exception");
		}

	}
}