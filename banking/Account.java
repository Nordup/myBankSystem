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
}