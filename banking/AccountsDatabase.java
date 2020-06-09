package banking;

import java.util.LinkedList;

class AccountsDatabase {
	LinkedList <Account>	acnts;

	public AccountsDatabase() {
		acnts = new LinkedList<Account>();
	}

	/**
	 * Find account with card number
	 * and pin and return it.
	 * If not finded return null
	 * @param cardNumber
	 * @param pin
	 * @return Account or null
	 */
	protected Account logIntoAccount(String cardNumber, String pin) {
		char checksum = cardNumber.charAt(cardNumber.length() - 1); // last digit
		char LuhnChecksum = (char)(BankCard.LuhnAlgorithmChecksum(cardNumber) + '0');

		if (checksum != LuhnChecksum) // if checksum wrong
			return null;
		for (Account acnt: acnts) {
			if (acnt.cardNumber.equals(cardNumber) && acnt.pin.equals(pin))
				return acnt;
		}
		return null;
	}

}