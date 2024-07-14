//imports needed for the program
import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * 
 * @author Shawn Wei
 * @description This program allows an online shopper to decrypt and encrypt
 *              their information safely while making online purchases/orders
 */
public class GustyGardensView extends JFrame {
	// JPanels
	private JPanel panelStart = new JPanel();
	private JPanel panelServer = new JPanel();
	private JPanel panelCustomer = new JPanel();
	private JPanel contentPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();

	// ----------------------------------------------------------------------
	private JLabel labelTitle; // The title of the program
	private JLabel labelTitle1; // The title of the program
	private JButton buttonCustomer = new JButton();
	private JButton buttonServer = new JButton();
	private ImageIcon galaxyIcon;
	private JLabel labelServerImage;

	// Customer panel
	private JLabel labelCustomer;
	private ImageIcon gardenIcon;
	private JLabel labelCustomerImage;
	private JLabel labelTomatoes;
	private JLabel labelCucumbers;
	private JLabel labelEggplants;
	private JLabel labelMelon;
	private JLabel labelBeans;
	private JLabel labelMushrooms;

	// combo boxes for the item and the produce items
	private JComboBox comboBoxTomatoes;
	private String tomatoesTypes[] = { "Cherry Tomato", "Roma Tomato", "Big Red" };
	private JComboBox comboBoxCucumbers;
	private String cucumbersTypes[] = { "English Cucumber", "Prickly Cucumbers" };
	private JComboBox comboBoxEggplants;
	private String eggplantTypes[] = { "Round Eggplant", "Long Eggplant" };
	private JComboBox comboBoxMelon;
	private String melonTypes[] = { "Winter Melon", "Watermelon" };
	private JComboBox comboBoxBeans;
	private String beanTypes[] = { "Green Beans", "Magic Beans" };
	private JComboBox comboBoxMushrooms;
	private String mushroomTypes[] = { "Super Mushroom", "Mega Mushroom", "1-UP Mushroom" };

	// label to help prompt the user
	private JLabel labelQuantityTomatoes;
	private JLabel labelQuantityCucumbers;
	private JLabel labelQuantityEggplants;
	private JLabel labelQuantityMelon;
	private JLabel labelQuantityBeans;
	private JLabel labelQuantityMushrooms;
	int[] quantity = new int[100];

	// This will help the user
	private JComboBox comboBoxQuantityTomatoes;
	private JComboBox comboBoxQuantityCucumbers;
	private JComboBox comboBoxQuantityEggplants;
	private JComboBox comboBoxQuantityMelon;
	private JComboBox comboBoxQuantityBeans;
	private JComboBox comboBoxQuantityMushrooms;

	// Labels for the purchase information
	private JLabel labelCredentials;
	private JLabel labelCreditCard;
	private JTextField tfCreditCard;// The credit card that the user will input
	private JLabel labelFullName;
	private JTextField tfFullName;// The name of the user
	private JLabel labelReminder;
	private JButton buttonCustomerBack;
	private JButton buttonPurchase;// this will encrypt all the purchase information

	private JCheckBox chkBoxReverse; // The user can select the reverse option
	private JCheckBox chkBoxFactor; // The user can select the factor option

	// Server Panel
	private JLabel labelServer;
	private JLabel labelRecentPurchases;
	private JList listOrders; // list to display the orders
	private String arrayOrders[] = new String[100];
	private JButton buttonServerBack;
	private ImageIcon adminImage;
	private JLabel labelAdminImage;
	private JTextArea txtDisplayArea;

	GustyGardensView.Music music = new GustyGardensView.Music();

	public GustyGardensView() {
		super("Encryption");
		setLayout(null);

		// initialize all of the panels
		startPanel();
		customerPanel();
		serverPanel();

		// add all of the panels to the content pane
		contentPanel.setLayout(cardLayout);
		contentPanel.add(panelStart, "Start");
		contentPanel.add(panelServer, "Server");
		contentPanel.add(panelCustomer, "Customer");

		this.setContentPane(contentPanel);
		// add music to my program
		 music.play();
		// show the start panel first
		cardLayout.show(contentPanel, "Start");

	}

	public void startPanel() {
		// SET THE START PANEL
		panelStart.setLayout(null);
		// set the background image
		galaxyIcon = new ImageIcon("Images/GustyGardensBackground.jpg");
		labelServerImage = new JLabel(galaxyIcon);
		labelServerImage.setSize(1000, 650);

		labelTitle = new JLabel("Gusty Garden's"); // Setting a title for the program
		labelTitle.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 65)); // setting the font and text size

		labelTitle1 = new JLabel("Produce");
		labelTitle1.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 65));

		buttonServer = new JButton("Server");
		buttonServer.setForeground(Color.RED);// Giving the server button a
												// color
		buttonServer.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 35));

		buttonCustomer = new JButton("Customer");
		buttonCustomer.setForeground(Color.BLUE);// Giving the server button a
													// color
		buttonCustomer.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 35));

		// adding all the labels and buttons that will appear on the Start panel
		panelStart.add(labelTitle);
		panelStart.add(labelTitle1);
		panelStart.add(buttonServer);
		panelStart.add(buttonCustomer);
		panelStart.add(labelServerImage);

		// Insets that allow us to place the GUI objects exactly where we want
		Insets insets = panelStart.getInsets();
		// The dimensions get the size of the GUI object
		Dimension size = labelTitle.getPreferredSize();
		int middle = (1000 - size.width) / 2;// position the label into the middle of the JPanel
		labelTitle.setBounds(middle + insets.left, 10 + insets.top, size.width, size.height);

		size = labelTitle1.getPreferredSize();
		middle = (1000 - size.width) / 2;
		labelTitle1.setBounds(middle + insets.left, 90 + insets.top, size.width, size.height);

		size = buttonServer.getPreferredSize();
		middle = (1000 - size.width) / 2;
		buttonServer.setBounds(middle + insets.left, 250 + insets.top, size.width, size.height);

		size = buttonCustomer.getPreferredSize();
		middle = (1000 - size.width) / 2;
		buttonCustomer.setBounds(middle + insets.left, 400 + insets.top, size.width, size.height);

	}

	public void customerPanel() {
		// SET THE CUSTOMER PANEL
		panelCustomer.setLayout(null);

		labelCustomer = new JLabel("Gusty Garden's Veggies"); // Setting a title for the program
		labelCustomer.setForeground(new java.awt.Color(0, 100, 0));// set the colour of the text
		labelCustomer.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 65));

		Insets insets = panelCustomer.getInsets(); // insets will get the JPanel space from top or left
		Dimension size = labelCustomer.getPreferredSize(); // size will get the size of the current object
		int middle = (1000 - size.width) / 2;
		labelCustomer.setBounds(middle + insets.left, 10 + insets.top, size.width, size.height);
		panelCustomer.add(labelCustomer);

		for (int i = 0; i < 100; i++) {
			quantity[i] = i;
		}
		String[] strQuantityArray = new String[quantity.length];
		for (int i = 0; i < quantity.length; i++) {
			strQuantityArray[i] = String.valueOf(quantity[i]);
		}

		// Tomatoes (note: the commenting will not be repeated, due to repetitiveness)
		labelTomatoes = new JLabel("Tomatoes"); // set the text for the produce label
		labelTomatoes.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));// set the font and the size of the text
		labelTomatoes.setBounds(100 + insets.left, 70 + insets.top, size.width, size.height);// position the label
																								// accordingly
		panelCustomer.add(labelTomatoes);// add the label to the Jpanel

		comboBoxTomatoes = new JComboBox(tomatoesTypes); // set up JComboBox
		comboBoxTomatoes.setBackground(new java.awt.Color(144, 238, 144)); // set the background of the combo box
		comboBoxTomatoes.setBounds(100 + insets.left, 125 + insets.top, 150, 40);// positioning the combo box
		panelCustomer.add(comboBoxTomatoes);// add the combo box to the Jpanel

		labelQuantityTomatoes = new JLabel("Quantity");// add the quantity label next to the produce type
		labelQuantityTomatoes.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));// set the font and size of the
																						// text
		labelQuantityTomatoes.setBounds(348 + insets.left, 70 + insets.top, size.width, size.height);// position the
																										// quantity
																										// information
																										// next to the
																										// produce
		panelCustomer.add(labelQuantityTomatoes);// add the quantity information

		comboBoxQuantityTomatoes = new JComboBox(strQuantityArray); // set up JComboBox
		comboBoxQuantityTomatoes.setBackground(new java.awt.Color(144, 238, 144)); // add a background colour to the
																					// quantity box
		comboBoxQuantityTomatoes.setBounds(350 + insets.left, 125 + insets.top, 50, 40);// position the quantity box
		panelCustomer.add(comboBoxQuantityTomatoes);// add the quantity box to the Jpanel

		// Cucumbers
		labelCucumbers = new JLabel("Cucumbers");
		labelCucumbers.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelCucumbers.setBounds(100 + insets.left, 145 + insets.top, size.width, size.height);
		panelCustomer.add(labelCucumbers);

		comboBoxCucumbers = new JComboBox(cucumbersTypes); // set up JComboBox
		comboBoxCucumbers.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxCucumbers.setBounds(100 + insets.left, 200 + insets.top, 150, 40);
		panelCustomer.add(comboBoxCucumbers);

		labelQuantityCucumbers = new JLabel("Quantity");
		labelQuantityCucumbers.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelQuantityCucumbers.setBounds(348 + insets.left, 145 + insets.top, size.width, size.height);
		panelCustomer.add(labelQuantityCucumbers);

		comboBoxQuantityCucumbers = new JComboBox(strQuantityArray); // set up
																		// JComboBox
		comboBoxQuantityCucumbers.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxQuantityCucumbers.setBounds(350 + insets.left, 200 + insets.top, 50, 40);
		panelCustomer.add(comboBoxQuantityCucumbers);

		// Eggplants
		labelEggplants = new JLabel("Eggplants");
		labelEggplants.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelEggplants.setBounds(100 + insets.left, 220 + insets.top, size.width, size.height);
		panelCustomer.add(labelEggplants);

		comboBoxEggplants = new JComboBox(eggplantTypes); // set up JComboBox
		comboBoxEggplants.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxEggplants.setBounds(100 + insets.left, 275 + insets.top, 150, 40);
		panelCustomer.add(comboBoxEggplants);

		labelQuantityEggplants = new JLabel("Quantity");
		labelQuantityEggplants.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelQuantityEggplants.setBounds(348 + insets.left, 220 + insets.top, size.width, size.height);
		panelCustomer.add(labelQuantityEggplants);

		comboBoxQuantityEggplants = new JComboBox(strQuantityArray); // set up
																		// JComboBox
		comboBoxQuantityEggplants.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxQuantityEggplants.setBounds(350 + insets.left, 275 + insets.top, 50, 40);
		panelCustomer.add(comboBoxQuantityEggplants);

		// Melons
		labelMelon = new JLabel("Melons");
		labelMelon.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelMelon.setBounds(100 + insets.left, 295 + insets.top, size.width, size.height);
		panelCustomer.add(labelMelon);

		comboBoxMelon = new JComboBox(melonTypes); // set up JComboBox
		comboBoxMelon.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxMelon.setBounds(100 + insets.left, 350 + insets.top, 150, 40);
		panelCustomer.add(comboBoxMelon);

		labelQuantityMelon = new JLabel("Quantity");
		labelQuantityMelon.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelQuantityMelon.setBounds(348 + insets.left, 295 + insets.top, size.width, size.height);
		panelCustomer.add(labelQuantityMelon);

		comboBoxQuantityMelon = new JComboBox(strQuantityArray); // set up
																	// JComboBox
		comboBoxQuantityMelon.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxQuantityMelon.setBounds(350 + insets.left, 350 + insets.top, 50, 40);
		panelCustomer.add(comboBoxQuantityMelon);

		// Beans
		labelBeans = new JLabel("Beans");
		labelBeans.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelBeans.setBounds(100 + insets.left, 370 + insets.top, size.width, size.height);
		panelCustomer.add(labelBeans);

		comboBoxBeans = new JComboBox(beanTypes); // set up JComboBox
		comboBoxBeans.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxBeans.setBounds(100 + insets.left, 425 + insets.top, 150, 40);
		panelCustomer.add(comboBoxBeans);

		labelQuantityBeans = new JLabel("Quantity");
		labelQuantityBeans.setBackground(new java.awt.Color(144, 238, 144));
		labelQuantityBeans.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelQuantityBeans.setBounds(348 + insets.left, 370 + insets.top, size.width, size.height);
		panelCustomer.add(labelQuantityBeans);

		comboBoxQuantityBeans = new JComboBox(strQuantityArray); // set up
																	// JComboBox
		comboBoxQuantityBeans.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxQuantityBeans.setBounds(350 + insets.left, 425 + insets.top, 50, 40);
		panelCustomer.add(comboBoxQuantityBeans);

		// Mushrooms
		labelMushrooms = new JLabel("Mushrooms");
		labelMushrooms.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelMushrooms.setBounds(100 + insets.left, 445 + insets.top, size.width, size.height);
		panelCustomer.add(labelMushrooms);

		comboBoxMushrooms = new JComboBox(mushroomTypes); // set up JComboBox
		comboBoxMushrooms.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxMushrooms.setBounds(100 + insets.left, 500 + insets.top, 150, 40);
		panelCustomer.add(comboBoxMushrooms);

		labelQuantityMushrooms = new JLabel("Quantity");
		labelQuantityMushrooms.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelQuantityMushrooms.setBounds(348 + insets.left, 445 + insets.top, size.width, size.height);
		panelCustomer.add(labelQuantityMushrooms);

		comboBoxQuantityMushrooms = new JComboBox(strQuantityArray); // set up
																		// JComboBox
		comboBoxQuantityMushrooms.setBackground(new java.awt.Color(144, 238, 144));
		comboBoxQuantityMushrooms.setBounds(350 + insets.left, 500 + insets.top, 50, 40);
		panelCustomer.add(comboBoxQuantityMushrooms);

		// Purchase credentials
		labelCredentials = new JLabel("Purchase Credentials");
		labelCredentials.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 40));
		labelCredentials.setBounds(520 + insets.left, 125 + insets.top, size.width, size.height);
		panelCustomer.add(labelCredentials);

		// Credit Card
		labelCreditCard = new JLabel("Credit Card Number:");
		labelCreditCard.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelCreditCard.setBounds(520 + insets.left, 180 + insets.top, size.width, size.height);
		panelCustomer.add(labelCreditCard);

		tfCreditCard = new JTextField();// text field to input information about the credit card
		tfCreditCard.setFont(new Font("Serif", Font.PLAIN, 20));
		tfCreditCard.setBounds(520 + insets.left, 240 + insets.top, 400, 25);
		panelCustomer.add(tfCreditCard);

		// Full Name
		labelFullName = new JLabel("Full Name:");
		labelFullName.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		labelFullName.setBounds(520 + insets.left, 260 + insets.top, size.width, size.height);
		panelCustomer.add(labelFullName);

		tfFullName = new JTextField();// text field to input information about the name
		tfFullName.setFont(new Font("Serif", Font.PLAIN, 20)); // Setting a cutsom font
		tfFullName.setBounds(520 + insets.left, 320 + insets.top, 250, 25);
		panelCustomer.add(tfFullName);

		// Security Level 1 (Reverse the Message)
		chkBoxReverse = new JCheckBox("Security level 1");
		chkBoxReverse.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 25));
		size = chkBoxReverse.getPreferredSize();
		chkBoxReverse.setBounds(520 + insets.left, 350 + insets.top, size.width, size.height);
		chkBoxReverse.setOpaque(false);// make the checkbox transparent
		panelCustomer.add(chkBoxReverse);

		// Security Level 2 (factor the Message)
		chkBoxFactor = new JCheckBox("Security level 2");
		chkBoxFactor.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 25));
		size = chkBoxFactor.getPreferredSize();
		chkBoxFactor.setBounds(520 + insets.left, 390 + insets.top, size.width, size.height);
		chkBoxFactor.setOpaque(false);// make the checkbox transparent
		panelCustomer.add(chkBoxFactor);

		// Reminder (remind the user to input proper things)
		labelReminder = new JLabel("*Credit Card or Name not entered");
		labelReminder.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 13));
		labelReminder.setForeground(Color.red);// red to capture the users attention
		size = labelReminder.getPreferredSize();
		labelReminder.setBounds(600 + insets.left, 450 + insets.top, size.width, size.height);
		labelReminder.setVisible(false);// hide this message if the user correctly inputs information
		panelCustomer.add(labelReminder);

		// purchase button
		buttonPurchase = new JButton("PURCHASE"); // purchase jbutton
		buttonPurchase.setForeground(new java.awt.Color(32, 178, 170));// setting the colour of the text
		buttonPurchase.setBackground(new java.awt.Color(173, 255, 47));// setting the colour of the background
		buttonPurchase.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 35));
		buttonPurchase.setBounds(600 + insets.left, 470 + insets.top, 250, 50);
		panelCustomer.add(buttonPurchase);

		// back button
		buttonCustomerBack = new JButton("Back");
		buttonCustomerBack.setForeground(new java.awt.Color(32, 178, 170));// setting the colour of the text
		buttonCustomerBack.setBackground(new java.awt.Color(173, 255, 47));// setting the colour of the background
		buttonCustomerBack.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 35));
		buttonCustomerBack.setBounds(600 + insets.left, 530 + insets.top, 250, 50);
		panelCustomer.add(buttonCustomerBack);

		gardenIcon = new ImageIcon("Images/CustomerBackground.jpg");// get the background image
		labelCustomerImage = new JLabel(gardenIcon);// use a Jlabel to display the image
		labelCustomerImage.setSize(1000, 650);// set the size of the image
		panelCustomer.add(labelCustomerImage);// add the image to the Jpanel
	}

	public void serverPanel() {
		// SET THE SERVER PANEL

		panelServer.setLayout(null);
		labelServer = new JLabel("Admin");
		// Setting a cutsom font for my title
		labelServer.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 65));

		Insets insets = panelServer.getInsets();
		Dimension size = labelServer.getPreferredSize();
		size = labelServer.getPreferredSize();
		int middle = (1000 - size.width) / 2;
		labelServer.setBounds(middle + insets.left, 30 + insets.top, size.width, size.height);
		panelServer.add(labelServer);

		// Recent purchases
		labelRecentPurchases = new JLabel("Recent Purchases");
		labelRecentPurchases.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 30));
		size = labelRecentPurchases.getPreferredSize();
		labelRecentPurchases.setBounds(100 + insets.left, 125 + insets.top, size.width, size.height);
		panelServer.add(labelRecentPurchases);

		txtDisplayArea = new JTextArea();
		txtDisplayArea.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		txtDisplayArea.setBounds(550 + insets.left, 180 + insets.top, size.width + 150, size.height + 250);
		panelServer.add(txtDisplayArea);

		listOrders = new JList(arrayOrders); // create with array Orders to display all the orders
		listOrders.setVisibleRowCount(5); // display five rows at once
		try {
			BufferedReader input = new BufferedReader(new FileReader("Files/Server.txt"));// read the text file that stores
																					// all the orders
			String aLine = input.readLine().trim();// read the first line

			int index = 0;
			// loop while the next line is not empty
			while (aLine != null) {
				arrayOrders[index++] = aLine.trim();// update the array orders
				aLine = input.readLine();// read the line into the array
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception");
		}

		// do not allow multiple selections
		listOrders.setForeground(Color.BLACK);// set the text to black
		listOrders.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 20));
		listOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// single selection mode on the list
		// add a JScrollPane containing JList to frame
		JScrollPane scrollPane = new JScrollPane(listOrders);
		scrollPane.setBounds(100 + insets.left, 200 + insets.top, 350, 250);
		panelServer.add(scrollPane);// add the scroll panel into the Jpanel

		// back button
		buttonServerBack = new JButton("Back");
		buttonServerBack.setForeground(Color.black);// Giving the back button a color
		buttonServerBack.setBackground(Color.LIGHT_GRAY);// Giving the back button a background color
		buttonServerBack.setFont(new Font("Dialog", Font.BOLD + Font.PLAIN, 35));
		buttonServerBack.setBounds(640 + insets.left, 500 + insets.top, 250, 50);
		panelServer.add(buttonServerBack);

		adminImage = new ImageIcon("Images/AdminBackground.jpg");// add the admin panel background
																						// image
		labelAdminImage = new JLabel(adminImage);
		labelAdminImage.setSize(1000, 650);
		panelServer.add(labelAdminImage);
	}

	/*
	 * clear all the text fields and information when we leave the panel empties all
	 * of the comboboxes, textfields, and checkboxes. This also clears the admin
	 * panel text area.
	 */
	public void clear() {
		tfCreditCard.setText("");
		tfFullName.setText("");
		chkBoxReverse.setSelected(false);
		chkBoxFactor.setSelected(false);
		comboBoxTomatoes.setSelectedIndex(0);
		comboBoxQuantityTomatoes.setSelectedIndex(0);
		comboBoxCucumbers.setSelectedIndex(0);
		comboBoxQuantityCucumbers.setSelectedIndex(0);
		comboBoxEggplants.setSelectedIndex(0);
		comboBoxQuantityEggplants.setSelectedIndex(0);
		comboBoxMelon.setSelectedIndex(0);
		comboBoxQuantityMelon.setSelectedIndex(0);
		comboBoxBeans.setSelectedIndex(0);
		comboBoxQuantityBeans.setSelectedIndex(0);
		comboBoxMushrooms.setSelectedIndex(0);
		comboBoxQuantityMushrooms.setSelectedIndex(0);

		txtDisplayArea.setText("");

	}

	// Display the order to the admin panel
	public void showOrder(String input) {
		txtDisplayArea.setText(input);
	}

	// Set the action listener for each slot to our Controller
	public void addListener(GustyGardensController controller) {
		buttonServer.addActionListener(controller);
		buttonCustomer.addActionListener(controller);

		comboBoxTomatoes.addItemListener(controller);
		comboBoxCucumbers.addItemListener(controller);
		comboBoxEggplants.addItemListener(controller);
		comboBoxMelon.addItemListener(controller);
		comboBoxBeans.addItemListener(controller);
		comboBoxMushrooms.addItemListener(controller);

		buttonCustomerBack.addActionListener(controller);
		buttonServerBack.addActionListener(controller);

		comboBoxQuantityTomatoes.addItemListener(controller);
		comboBoxQuantityCucumbers.addItemListener(controller);
		comboBoxQuantityEggplants.addItemListener(controller);
		comboBoxQuantityMelon.addItemListener(controller);
		comboBoxQuantityBeans.addItemListener(controller);
		comboBoxQuantityMushrooms.addItemListener(controller);

		chkBoxReverse.addItemListener(controller);
		chkBoxFactor.addItemListener(controller);
		buttonPurchase.addActionListener(controller);

		listOrders.addListSelectionListener(controller);
	}

	// show the desired panel when a button is clicked
	public void show(String action) {
		if (action.equals("Back")) {
			action = "Start";
		}
		// show the desired contentPanel using the action
		cardLayout.show(contentPanel, action);

	}//end show

	public String[] getProduce() {

		// set the tomato type ordered
		String strTomato = tomatoesTypes[(comboBoxTomatoes.getSelectedIndex())];

		// set the cucumber type ordered
		String strCucumber = cucumbersTypes[(comboBoxCucumbers.getSelectedIndex())];

		// set the eggplant type ordered
		String strEggplants = eggplantTypes[(comboBoxEggplants.getSelectedIndex())];

		// set the melon type ordered
		String strMelon = melonTypes[(comboBoxMelon.getSelectedIndex())];

		// set the beans type ordered
		String strBeans = beanTypes[(comboBoxBeans.getSelectedIndex())];

		// set the mushroom type ordered
		String strMushrooms = mushroomTypes[(comboBoxMushrooms.getSelectedIndex())];

		// initialize arrays for the items
		String[] vegetableTypeArray = { strTomato, strCucumber, strEggplants, strMelon, strBeans, strMushrooms };

		return vegetableTypeArray;
	}// end getProduce

	public int[] getQuantity() {
		// CHECK FOR THE QUANTITY OF EACH VEGETABLE

		// set the tomato type ordered
		int intTomato = quantity[(comboBoxQuantityTomatoes.getSelectedIndex())];

		// set the cucumber type ordered
		int intCucumber = quantity[(comboBoxQuantityCucumbers.getSelectedIndex())];

		// set the eggplant type ordered
		int intEggplants = quantity[(comboBoxQuantityEggplants.getSelectedIndex())];

		// set the melon type ordered
		int intMelon = quantity[(comboBoxQuantityMelon.getSelectedIndex())];

		// set the beans type ordered
		int intBeans = quantity[(comboBoxQuantityBeans.getSelectedIndex())];

		// set the mushroom type ordered
		int intMushrooms = quantity[(comboBoxQuantityMushrooms.getSelectedIndex())];

		// initialize arrays for number of items
		int[] vegetableQuantityArray = { intTomato, intCucumber, intEggplants, intMelon, intBeans, intMushrooms };

		return vegetableQuantityArray;
	}// end getQuantity

	// set the reminders to the user
	public void setReminder(int choice) {
		// if the reminder needs to be set
		if (choice == 0 || choice == 1) {
			// If they did not select and products
			if (choice == 0) {
				labelReminder.setText("*Please select an item.");
			} else {
				// if they did not enter their credentials
				labelReminder.setText("*Credit Card or Name not entered");
			}
			labelReminder.setVisible(true);// allows this message to be visible
		} else {
			labelReminder.setVisible(false);// allows this message to not be visible
		}
	}// end setReminder

	// get the name from the text field
	public String getName() {
		// if the text field is empty return an empty string
		if (tfFullName.getText().isEmpty()) {
			return "";
		} else {
			// return the name received from the text field
			String strFullName = tfFullName.getText();
			return strFullName;
		}

	}// end getName

	public String getCreditCard() {
		// if the text field is empty return an empty string
		if (tfCreditCard.getText().isEmpty()) {
			return "";
		} else {
			// return the credit card received from the text field
			String strCreditCardNumber = tfCreditCard.getText();
			return strCreditCardNumber;
		}

	}// end getCreditCard

	// check if the reverse check box is checked
	public boolean reverseChecked() {

		boolean reverseChecked = chkBoxReverse.isSelected() ? true : false;
		return reverseChecked;
	}// end reverseChecked

	// check if the factor check box is checked
	public boolean factorChecked() {
		boolean factorChecked = chkBoxFactor.isSelected() ? true : false;
		return factorChecked;
	}// end factorChecked

	// get the file name from the array of orders
	public String getFileName() {
		String fileName = arrayOrders[listOrders.getSelectedIndex()];
		return fileName;
	}// end getFileName

	// Allow music to play while the user is shopping
	public class Music {
		Clip clip;
		AudioInputStream sound;

		// play the music
		public void play() {
			try {
				// get the music file from the correct saved file
				File file = new File("Shop-OST.wav");
				sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);// open the sound to be used
				clip.loop(clip.LOOP_CONTINUOUSLY);// loop the music
				clip.start();// start the music
			} catch (Exception e) {
			}
		}// end method play
	}// end class music
}
