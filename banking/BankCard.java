package banking;

import java.util.Random;

class BankCard {
	
	/**
	 * Create card number with
	 * IIN 400000, random customer account number
	 * and Luhn algorithm checksum
	 * @return
	 */
	protected static String createNumber() {
		Random	rand = new Random();
		String	IIN = "400000";
		String	cardNumber = "";
		int		CAN; // customer account number

		CAN = rand.nextInt(1000000000);
		cardNumber = String.valueOf(CAN); // rand int CAN add to cardNumber
		while (cardNumber.length() < 9) { // add zeros to begin
			cardNumber = "0" + cardNumber;
		}
		cardNumber = IIN + cardNumber;
		
		// add checsum to cardNumber
		cardNumber += LuhnAlgorithmChecksum(cardNumber); // return Luhn algoritgm checksum
		return cardNumber;
	}

	protected static String createPin() {
		Random	rand = new Random();
		String	pin;

		pin = String.valueOf(rand.nextInt(10000));
		while (pin.length() < 4) {
			pin = "0" + pin;
		}
		return pin;
	}

	/**
	 * Find Luhn algorithm checksum
	 * and return it
	 * @param cardNbr
	 * @return
	 */
	protected static int LuhnAlgorithmChecksum(String cardNbr) {
		int		dgt; // for digits of card
		int		sum = 0;
		int		checksum;

		for (int i = 0; i < 15; i++) {
			dgt = cardNbr.charAt(i) - '0';
			if (i % 2 == 0) // multiply odd digits by 2
				dgt *= 2;
			if (dgt > 9) // substract 9 to numbers over 9
				dgt -= 9;
			sum += dgt; // add all numbers
		}
		//find checksum
		if (sum % 10 == 0) {
			checksum = 0;
		}
		else {
			checksum = 10 - sum % 10;
			/**
			 * example:	if sum == 56 then 56 % 10 = 6
			 * 			10 - 6 = 4 - that is checksum
			 */
		}
		return checksum;
	}
}