package banking;

import java.sql.*;

class Database { // SQLite JDBC
	Connection	con;
	Statement	stmnt;
	ResultSet	rs;

	public Database() {
		con = null;
		stmnt = null;
		rs = null;
	}

	/**
	 * insert into table card
	 * @param number
	 * @param pin
	 * @return
	 */
	protected int insertInto(String number, String pin) {
		try {
			String createAcc = "INSERT INTO card (number, pin)\n"
							+  "	VALUES ('" + number + "', '" +  pin + "'\n"
							+  ");";

			stmnt.execute(createAcc);
		} catch (SQLException e) {
			Output.putstr(e.getMessage() + "\ncreateTableAccounts() fails\n");
			return 1;
		}
		return 0;
	}

	/**
	 * check new card number for existing in database
	 * @param new_number
	 * @return
	 */
	protected int checkCardNumber(String new_number) {
		try {
			String tmp_nmbr; // temporary number

			rs = stmnt.executeQuery("SELECT number FROM card;"); // select from database table
			while (rs.next()) { // for every result
				// read the result set
				tmp_nmbr = rs.getString("number"); // get string from column number

				if (new_number.equals(tmp_nmbr)) { // if this cardNumber exist
					return 0; // finded
				}
			}
		} catch (SQLException e) {
			Output.putstr(e.getMessage() + "\ncreateTableAccounts() fails\n");
			return 2; // error
		}
		return 1; // not finded
	}

	/**
	 * create connection to database
	 * with sqlite drivers
	 * and create statement
	 * @param database
	 * @param out
	 * @return
	 */
	protected int connect(String database) {
		try {
			String url = "jdbc:sqlite:" + database;
			//create connection to the database
			con = DriverManager.getConnection(url);
			//create statement
			stmnt = con.createStatement();
		} catch (SQLException e) {
			Output.putstr(e.getMessage() + "\n");
			return 1;
		}
		if (createTableAccounts() == 0) // create table in database
			return 0;
		else
			return 1;
	}

	/**
	 * create table if it's not exist
	 */
	protected int createTableAccounts() {
		try {
			String createAcc = "CREATE TABLE IF NOT EXISTS card (\n"
							+  "	id		INTEGER PRIMARY KEY,\n"
							+  "	number	TEXT,\n"
							+  "	pin		TEXT,\n"
							+  "	balance	INTEGER DEFAULT 0\n"
							+  ");";

			stmnt.execute(createAcc);
		} catch (SQLException e) {
			Output.putstr(e.getMessage() + "\ncreateTableAccounts() fails\n");
			return 1;
		}
		return 0;
	}
}