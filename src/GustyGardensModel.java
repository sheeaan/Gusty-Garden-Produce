/**
 * 
 * @author Shawn Wei
 * @version January 23, 2022
 * @course ICS4U1
 * @description This model class does all the encrypting for the files, ensuring
 *              that the information kept by the user is safely stored in a file
 */
public class GustyGardensModel {
	// Constants this program will need
	private final int TABLE_SIZE = 95;
	private final int TABLE_MIN = 32;
	private final int TABLE_MAX = 126;

	private GustyGardensController controller;
	private GustyGardensView view;

	// The shiftKey and factor is needed for multiple methods
	// This number can be adjusted to have different encrypted messages.
	private int shiftKey = 45;
	private int factor = 44;

	//This will check if the 
	private boolean blnReverseOption = false;
	private boolean blnFactorOption = false;

	public String Encryption(String msg) {
		String encryptedMessage = "";
		// If no option is selected, base encryption
		if (blnReverseOption == false && blnFactorOption == false) {
			encryptedMessage = cipher(msg, shiftKey);// calling the cipher method
		}
		// If only option 2 is selected
		else if (blnReverseOption == true && blnFactorOption == false) {
			encryptedMessage = cipherReverse(msg, shiftKey, blnReverseOption); // calling the cipherReverse method
		}
		// If only option 3 is selected
		else if (blnReverseOption == false && blnFactorOption == true) {
			encryptedMessage = cipherFactor(msg, shiftKey, factor);// calling the cipherFactor method
		}

		// If option 2 and 3 are selected
		else if (blnReverseOption == true && blnFactorOption == true) {
			msg = cipherReverse(msg, shiftKey, blnReverseOption);// calling the cipherReverse the msg with it
			encryptedMessage = cipherFactor(msg, shiftKey, factor);
		}
		return encryptedMessage;

	}

	public String Decryption(String msg) {
		String decryptedMessage = "";
		// If no option is selected, base encryption
		if (blnReverseOption == false && blnFactorOption == false) {
			decryptedMessage = decipher(msg, shiftKey);
		}
		// If only option 2 is selected
		else if (blnReverseOption == true && blnFactorOption == false) {
			decryptedMessage = decipherReverse(msg, shiftKey, blnReverseOption);
		}
		// If only option 3 is selected
		else if (blnReverseOption == false && blnFactorOption == true) {
			decryptedMessage = decipherFactor(msg, shiftKey, factor);
		}
		// If option 1, 2 and 3 are selected
		else if (blnReverseOption == true && blnFactorOption == true) {

			msg = decipherFactor(msg, shiftKey, factor);// calling the decipherFactor method, and replacing // the
			// msg with it
			decryptedMessage = decipherReverse(msg, shiftKey, blnReverseOption);// calling the decipherReverse method, and replacing the msg with it
		}
		return decryptedMessage;
	}

	
	/**
	 * method to set the blnReverseOption
	 * 
	 * @param reverse
	 */
	public void setBlnReverseOption(boolean reverseBln) {
		blnReverseOption = reverseBln; // store the blnReverseOption
	} // end method setBlnReverseOption
	
	/**
	 * method to set the setblnFactorOption
	 * 
	 * @param factorBln
	 */
	public void setBlnFactorOption(boolean factorBln) {
		blnFactorOption = factorBln; // store the blnFactorOption
	} // end method setBlnFactorOption

	/**
	 * method to retrieve the reverseOption
	 * 
	 * @return blnReverseOption
	 */
	public boolean getBlnReverseOption() {
		return blnReverseOption;
	}
	
	/**
	 * method to retrieve the FactorOption
	 * 
	 * @return blnFactorOption
	 */
	public boolean getBlnFactorOption() {
		return blnFactorOption;
	}
	
	
	/**
	 *  @description: Encrypts the message by a key 
	 *  @Params: msg - the given message, shift - the
	 * given key Returns: the encrypted message
	 **/
	public String cipher(String msg, int shift) {
		String strMsg = "";
		int length = msg.length();
		// loop for every character in the string
		for (int x = 0; x < length; x++) {
			// shift the character using the caeser cipher method.
			int position = (msg.charAt(x) + shift);
			//if it exceeds the ascii table size, start from the beginning again.
			while (position > TABLE_MAX) {
				position = position - TABLE_SIZE;
			}
			//set the new encrypted message
			strMsg = strMsg + (char) (position);
		}
		return strMsg;
	}

	/**
	 * @description: Add some random letters at the start and end of the message before encrypting
	 * it. 
	 * @Params: msg - the given message, shift - the given key, randomString1 -
	 * random letters at the front, randomString2 - random letters at the end
	 * Returns: the encrypted message
	 **/
	
	// NOTE: !! This feature was originally added to the base encryption program, but has
	// been omitted from the shop (for convenience reasons)
	public String cipherRandomString(String msg, int shift, String randomString1, String randomString2) {
		String strMsg = "";
		//add the random characters into the string
		msg = randomString1 + msg + randomString2;
		int length = msg.length();
		// loop for every character in the string
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) + shift);
			char c = (char) (position);
			//if it exceeds the ascii table size, start from the beginning again.
			if (position > TABLE_MAX) {
				while (position > TABLE_MAX) {
					position = position - TABLE_SIZE;
				}
				//set the new encrypted message
				strMsg = strMsg + (char) (position);
			} else {
				//set the new encrypted message
				strMsg = strMsg + (char) (msg.charAt(x) + shift);
			}
		}
		return strMsg;
	}

	/**
	 *  @description: Reverse your message before encrypting it. 
	 *  @Params: msg - the given message,
	 * shift - the given key, reverse - to diffrenciate between the cipher method
	 * Returns: the encrypted message
	 **/
	public String cipherReverse(String msg, int shiftKey, boolean reverse) {
		String strMsg = "";
		int length = msg.length();
		char[] msgArray = new char[length];
		//reverse the string for added security/encryption
		for (int i = 0; i < msgArray.length; i++) {
			msgArray[length - i - 1] = msg.charAt(i);
		}
		msg = msg.copyValueOf(msgArray);//returns a String that represents the characters of a char array

		//shift the message
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) + shiftKey);
			while (position > TABLE_MAX) {
				position = position - TABLE_SIZE;
			}
			strMsg = strMsg + (char) (position);
		}
		return strMsg;
	}

	/**
	 * @description: Multiply each character of your message by a number before encrypting it.
	 * @Params: msg - the given message, shift - the given key, factor - the factor
	 * in which each character will multiply by Returns: the encrypted message
	 **/
	public String cipherFactor(String msg, int shiftKey, int factor) {
		String strMsg = "";
		int length = msg.length();
		//multiply each character by the factor
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) * factor + shiftKey);

			while (position > TABLE_MAX) {
				position = position - TABLE_SIZE;
			}
			strMsg = strMsg + (char) (position);
		}
		return strMsg;
	}

	/**
	 * @description: Decrypts the message with a given key 
	 * @Params: msg - the given message, shift
	 * - the given key Returns: the decrypted message
	 **/
	public String decipher(String msg, int shift) {
		String strMsg = "";
		int length = msg.length();
		//deccrypt each character by reverse operating on the ascii table
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) - shift);
			while (position < TABLE_MIN) {
				position = position + TABLE_SIZE;
			}
			strMsg = strMsg + (char) (position);
		}
		return strMsg;
	}

	/**
	 *  @description: Decrypts a message that has some random letters at the start and end of the
	 * message. 
	 * @Params: msg - the given message, shift - the given key,
	 * randomString1 - random letters at the front, randomString2 - random letters
	 * at the end Returns: the decrypted message
	 **/
	// NOTE: !!This feature was originally added to the base encryption program, but has
	// been omitted from the shop (for convenience reasons)
	public String decipherRandomString(String msg, int shift, String randomString1, String randomString2) {
		String strMsg = "";
		int length1 = randomString1.length();
		int length2 = randomString2.length();
		//subtract the random strings from the message
		msg = msg.substring(length1, msg.length() - length2);
		int length = msg.length();
		//decrypt the message
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) - shift);
			char c = (char) (position);//find the position of each character
			//If the decryption is less than the lowest value on the ascii table, go the the end of the ascii table
			if (position < TABLE_MIN) {
				while (position < TABLE_MIN) {
					position = position + TABLE_SIZE;
				}
				strMsg = strMsg + (char) (position);
			} else {
				strMsg = strMsg + (char) (msg.charAt(x) - shift);
			}
		}
		return strMsg;
	}

	/**
	 *  @description: Decrypts a reversed encrypted message 
	 *  @Params: msg - the given message, shift
	 * - the given key, reverse - to diffrenciate between the cipher method Returns:
	 * the decrypted message
	 **/
	public String decipherReverse(String msg, int shiftKey, boolean reverse) {
		String strMsg = "";
		int length = msg.length();
		char[] msgArray = new char[length];
		//store the string into a char array
		for (int i = 0; i < msgArray.length; i++) {
			msgArray[length - i - 1] = msg.charAt(i);
		}
		msg = msg.copyValueOf(msgArray);//returns a String that represents the characters of a char array
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x) - shiftKey);
			while (position < TABLE_MIN) {
				position = position + TABLE_SIZE;
			}
			strMsg = strMsg + (char) (position);
		}
		return strMsg;
	}

	/**
	 * @description: Decrypts a message with each character of your message multiplied by a
	 * number. 
	 * @Params: msg - the given message, shift - the given key, factor - the
	 * factor in which each character will multiply by Returns: the decrypted
	 * message
	 * @easter_egg_message: This was the hardest thing to code in the whole program
	 **/
	public String decipherFactor(String msg, int shiftKey, int factor) {
		String strMsg = "";
		// get the range of the encrypted letter
		final int RANGE_MIN = TABLE_MIN * factor + shiftKey;
		final int RANGE_MAX = TABLE_MAX * factor + shiftKey;
		int length = msg.length(); // Get the message length
		for (int x = 0; x < length; x++) {
			int position = (msg.charAt(x));
			int originalposition = position;
			int currentposition = position;
			// Using the while loop to set the position into the range
			while (position < RANGE_MIN) {
				// add 95(table size) until the position is in the range - reverse the operation
				// when the program encrypts a string by subtracting 95 until it's in the table
				// range
				position = position + TABLE_SIZE;
			}
			while (position < RANGE_MAX) {
				// reverse encryption operation, subtract shift then divide by the factor, if
				// there is reminder then move onto next position by adding 95 (table size)
				if ((position - shiftKey) % factor > 0) {
					if ((position - shiftKey) % factor == 0) {
						currentposition = ((position - shiftKey) / factor) * factor + shiftKey; // find the calculated
																								// position
						// use the while loop to find the position within the table
						while (currentposition > TABLE_MAX) {
							currentposition = currentposition - TABLE_SIZE;
						}
						// compare with the position of encrypted char, if the calculated position is
						// equal to the position of encrypted char, then calculate the actual position
						// of the char being encrypted and use the while loop to set it to the position
						// within the table, then exit the while loop
						if (currentposition == originalposition) {
							currentposition = (position - shiftKey) / factor;
							while (currentposition < TABLE_MIN) {
								currentposition = currentposition + TABLE_SIZE;
							}
							while (currentposition > TABLE_MAX) {
								currentposition = currentposition - TABLE_SIZE;
							}
							break; // if the actual position of the char being
									// encrypted is found, exit the while
									// loop
						}
					}
				}
				// reverse encryption operation, subtract shift then divide by the factor, if
				// there is no reminder then look further to calculate and find the actual
				// position
				if ((position - shiftKey) % factor == 0) {
					currentposition = ((position - shiftKey) / factor) * factor + shiftKey;
					currentposition = (currentposition - shiftKey) / factor; // find the calculated position
					// use the while loop to find the postion within the table
					while (currentposition < TABLE_MIN) {
						currentposition = currentposition + TABLE_SIZE;
					}
					while (currentposition > TABLE_MAX) {
						currentposition = currentposition - TABLE_SIZE;
					}
					// compare with the position of encrypted char, if the caculated position is
					// equal to the position of encrypted char, then calculate the actual position
					// of the char being encrypted and use the while loop to set it to the postion
					// within the table, then exit the while loop
					if (currentposition == originalposition) {
						while (currentposition < TABLE_MIN) {
							currentposition = currentposition + TABLE_SIZE;
						}
						while (currentposition > TABLE_MAX) {
							currentposition = currentposition - TABLE_SIZE;
						}
						break; // if the actual position of the char being encrypted is found, exit the while
								// loop
					}
				}
				position = position + TABLE_SIZE; // if doesn't match then add 95 (table size) to move to next postion
			}
			strMsg = strMsg + (char) (currentposition); // Convert into a string
		}
		return strMsg; // return the decrypted message
	}

}// end GustyGardensModel