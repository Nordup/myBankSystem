package banking;

class Account {
	String	cardNumber;
	String	pin;
	int		balance;

	public Account(String cardNumber, String pin) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		balance = 0;
	}

	public int getBalance() {
		return balance;
	}

	/**
	 * Add new account to database with
	 * card number from CardNumber.create() and
	 * random pin
	 * @return
	 */
	protected static int createNew(Database dbase) {
		String	cardNumber = "";
		String	pin;
		boolean	created = false; // cardNumber created?

		while (!created) { // while not created unique card number
			cardNumber = BankCard.createNumber();

			int log = dbase.checkCardNumber(cardNumber);
			if (log == 2)
				return 1; // error
			else if (log == 1) // created new cardNumber
				created = true;
		}
		pin = BankCard.createPin(); // create **** pin
		
		if (dbase.insertInto(cardNumber, pin) != 0)
			return 1; // error code
		Output.printCardCreated(cardNumber, pin); // put "card created string"
		return 0;
	}
}