package banking;

import java.util.Scanner;

public class Main {
	Database			dbase;
	AccountsDatabase	acntsDb;
	Scanner				scan;

	protected Main() {
		dbase = new Database();
		acntsDb = new AccountsDatabase();
		scan = new Scanner(System.in);
	}

	/**
	 * return database string
	 * if args wrong put "usage"
	 * and return 'null'
	 * @param args
	 * @return
	 */
	private String arguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-filename")) { // if find -filename option
				if (args[i + 1] != null) // if arg exist
					return args[i + 1]; // return arg
				break;
			}
		}
		Output.printUsage();
		return null;
	}

	/**
	 * Just loop startSystem()
	 * until he returns !1
	 * @param args
	 */
	public static void main(String[] args) {
		String	database;
		int		log = 1; // 0 is exit, 1 is continue
		Main	main;

		main = new Main();
		database = main.arguments(args);
		if (database == null) // if we doesn't have "-filename file"
			System.exit(1);
		if (main.dbase.connect(database) == 1) // connect ot datatbase
			System.exit(1);
		while (log == 1) {
			log = main.startSystem();
		}
		if (log == 0) {
			Output.printBye();
		}
		/**
		 * some errors handling
		 */
	}

	/**
	 * Put menu, read command and do
	 * what user write or put error massege
	 * @return 0 if we want to exit programm
	 * 1 if we don't want
	 */
	private int startSystem() {
		String	cmd = "";

		while (cmd.length() < 1) {
			Output.printMenu();
			
			cmd = scan.next();
			switch (cmd) {
				case "0":
					return 0; // exit the programm
				case "1":
					if (BankCard.createNew(dbase) != 0) { // add new account into database
						Output.putstr("Cannot create new card...\n");
						return 2; // error code
					}
					break;
				case "2":
					if (accountLogging() == 0)
						return 0; // exit
					break;
				default:
					Output.wrongAnswer();
					cmd = ""; // to try enter number again
			}
		}

		return 1;
	}

	/**
	 * Read card number, pin.
	 * Return 1 if account is not finded
	 * else call insideAccount()
	 * @return 0 to exit program
	 */
	private int accountLogging() {
		Output.putstr("\nEnter your card number:\n");
		String	cardNumber = scan.next();
		Output.putstr("Enter your PIN:\n");
		String	pin = scan.next();

		if (cardNumber.length() != 15)
			cardNumber = "000000000000000";
		Account acnt = acntsDb.logIntoAccount(cardNumber, pin);
		if (acnt != null) { // "null" mean that account is not finded
			Output.putstr("\nYou have successfully logged in!\n\n");
			if (insideAccount(acnt) == 0)
				return 0; // exit from program
		}
		else
			Output.putstr("\nWrong card number or PIN!\n\n");
		return 1; // return to main menu
	}
	/**
	 * Put account menu.
	 * Read command and:
	 * put acnt balance,
	 * log Output or exit program
	 * @param acnt; account that we logged in
	 * @return
	 */
	private int insideAccount(Account acnt) {
		int log = 1;

		while (log == 1) {
			String cmd = "";

			while (cmd.length() < 1) {
				Output.printAccountMenu();
				cmd = scan.next();

				switch (cmd) {
					case "1":
						Output.putstr("\nBalance: " + acnt.getBalance() + "\n\n");
						break;
					case "2":
						Output.putstr("\nYou have successfully logged Output!\n\n");
						return 1;
					case "0":
						return 0;
					default:
						Output.wrongAnswer();
						cmd = "";
				}
			}
		}
		return 0;
	}
}