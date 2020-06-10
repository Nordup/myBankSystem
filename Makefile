PACKAGE = banking/
OUT = ./out/
FILES = Main.java Output.java BankCard.java Database.java
CLASSES = $(FILES:%.java=%.class)
PATH_FILES = $(addprefix $(PACKAGE), $(FILES))
OUT_FILES = $(addprefix $(addprefix $(OUT), $(PACKAGE)), $(CLASSES))

all: $(OUT_FILES)

$(OUT_FILES): $(PATH_FILES)
	javac -classpath ./$(PACKAGE) -d $(OUT) $(PATH_FILES)

clean:
	rm -rf $(OUT_FILES)

fclean: clean

re: fclean all

.PHONY: all clean fclean re
