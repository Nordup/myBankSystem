# myBankSystem
Veeery simple imitation of bank system

## Description:
CLI application with JDBC sqlite. Ability to create account, delete it, add "money" and transfer them to other account.

## Build:
```
gradle build
```

## Usage:
- Run
  ```
  gradle run --args="-filename [path to database]"
  ```
  
## Example:
```
gradle run --args="-filename databases/database.db"
> Task :run

1. Create an account
2. Log into account
0. Exit
2

Enter your card number:
4000005329396387

Enter your PIN:
6847

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
3

Transfer
Enter card number:
4000000335275576

Enter how much money you want to transfer:
77

Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
0

Bye!
```
- Before
```
sqlite> .schema
CREATE TABLE card (
	id		INTEGER PRIMARY KEY,
	number	TEXT,
	pin		TEXT,
	balance	INTEGER DEFAULT 0
);
sqlite> SELECT * FROM card;
2|4000005329396387|6847|4077
3|4000000335275576|0518|123
```

- After
```
sqlite> SELECT * FROM card;
2|4000005329396387|6847|4000
3|4000000335275576|0518|200
```
