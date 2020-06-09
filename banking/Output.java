package banking;

class Output {

	//put string
	protected static void putstr(String str) {
		System.out.print(str);
	}
	
	//there are methods that puts some string
	protected static void printUsage() {
		System.out.println("\tUsage: ...");
	}
	protected static void printAccountMenu() {
		String menu = "1. Balance\n2. Log out\n0. Exit";
		System.out.println(menu);
	}
	protected static void printMenu() {
		String menu = "1. Create an account\n2. Log into account\n0. Exit\n";
		System.out.print(menu);
	}
	protected static void wrongAnswer() {
		System.out.println("\nwrong answer try again!\n");
	}
	protected static void printBye() {
		System.out.println("\nBye!");
	}
	
	/**
	 * Put card created success string
	 * @param acnt
	 */
	protected static void printCardCreated(String cardNumber, String pin) {
		System.out.println("\nYour card has been created");
		System.out.println("Your card number:");
		System.out.println(cardNumber);
		System.out.println("Your card PIN:");
		System.out.println(pin + "\n");
	}
}