cpath=./out/
jdbc_lib="./libs/sqlite-jdbc-3.30.1.jar"
class=banking.Main
database="-filename ./database/bank.db"

java -classpath $cpath:$jdbc_lib $class $database