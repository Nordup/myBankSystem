package banking;

import java.util.LinkedList;
import java.util.Random;

class AccountsDatabase {
	SQLiteJDBC				sql;
	LinkedList <Account>	acnts;
	Random					rand;

	public AccountsDatabase() {
		sql = new SQLiteJDBC();
		acnts = new LinkedList<Account>();
		rand = new Random();
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
		char LuhnChecksum = (char)(CardNumber.LuhnAlgorithmChecksum(cardNumber) + '0');

		if (checksum != LuhnChecksum) // if checksum wrong
			return null;
		for (Account acnt: acnts) {
			if (acnt.cardNumber.equals(cardNumber) && acnt.pin.equals(pin))
				return acnt;
		}
		return null;
	}

	/**
	 * Add new account to database with
	 * card number from CardNumber.create() and
	 * random pin
	 * @return
	 */
	protected int createAccount() {
		String	cardNumber = "";
		Account	new_acnt;
		String	pin;
		boolean	created = false; // cardNumber created?

		while (!created) {
			cardNumber = CardNumber.create(rand);
			created = true; // created new cardNumber 

			for (Account acnt: acnts) {
				if (acnt.cardNumber.equals(cardNumber)) { // if this cardNumber exist
					created = false;
					break;
				}
			}
		}

		pin = String.valueOf(rand.nextInt(10000));
		while (pin.length() < 4) {
			pin = "0" + pin;
		}


		new_acnt = new Account(cardNumber, pin);
		acnts.add(new_acnt);
		Output.printCardCreated(new_acnt);
		return 0;
	}
}