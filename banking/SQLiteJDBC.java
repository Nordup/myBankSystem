package banking;

import java.sql.*;

class SQLiteJDBC {
	Connection	con;
	Statement	stmnt;
	ResultSet	rs;

	public SQLiteJDBC() {
		con = null;
		stmnt = null;
		rs = null;
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
			String createAcc = "CREATE TABLE IF NOT EXISTS accounts (\n"
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