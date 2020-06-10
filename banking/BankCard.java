package banking;

import java.util.Random;

class BankCard {
	private final String	number;
	private String			pin;
	private int				balance;
	private boolean			loggedin;

	//Constructors:
	private BankCard(String number) {
		this.number = number;
		this.loggedin = false;
	}
	//end of Constructors


	//Setters: update in database table
	private int setPin(String pin) { //rewrite
		this.pin = pin;
		return 0;
	}
	protected int addToBalance(int add) { //rewrite
		this.balance += add;
		return 0;
	}
	protected int subtractFromBalance(int subs) { //rewrite
		this.balance -= subs;
		return 0;
	}
	//end of Setters
	//Getters:
	protected String getNumber() {
		return number;
	}
	protected String getPin() {
		return pin;
	}
	protected int getBalance() {
		return balance;
	}
	//end of Getters


	//Static methods:
	/**
	 * Add new card into database with
	 * new card number from CardNumber.create() and
	 * with pin from createPin()
	 * @return
	 */
	protected static int createNew(Database dbase) {
		String	cardNumber = "";
		String	pin;
		boolean	created = false; // cardNumber created?

		while (!created) { // while not created unique card number
			cardNumber = BankCard.createNumber();

			int log = dbase.checkNewCardNumber(cardNumber); // check new card if it's exists in database
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
	/**
	 * Create card number with
	 * IIN 400000, random customer account number
	 * and Luhn algorithm checksum
	 * @return
	 */
	private static String createNumber() {
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
	/**
	 * create ranrom pin
	 * @return
	 */
	private static String createPin() {
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
	private static int LuhnAlgorithmChecksum(String cardNbr) {
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
	//end of Static methods
}