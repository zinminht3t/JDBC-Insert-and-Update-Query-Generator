package querystring.generator;

import java.util.ArrayList;
import java.util.Scanner;

public class QueryGenerator {

	static ArrayList<String> columnNames = new ArrayList<String>();
	static ArrayList<String> variableNames = new ArrayList<String>();
	static String tableName = null;
	private static Scanner scanner;
	static String inputId;
	static String inputVari;

	public static void main(String[] args) {
		System.out.println("Insert Query or Update Query? Type Insert for I or Update for U");
		String input = ReadConsole();

		Boolean askAgain = true;
		while (askAgain) {
			if (input.toUpperCase().equals("I")) {
				tableName = AskTableName();
				askColumnsAndVariables();
				insertPrintOutput();
				askAgain = false;
			} else if (input.toUpperCase().equals("U")) {
				tableName = AskTableName();
				askColumnsAndVariables();
				askWhereClauseIdAndVariable();
				updatePrintOutput();
				askAgain = false;
			} else {
				System.err.println("Please Type only I or U!");
			}
		}
	}

	private static String AskTableName() {
		System.out.println("Please enter the table name!");
		return ReadConsole();
	}

	private static void askColumnsAndVariables() {

		Boolean askAgainCV = true;
		String inputC;
		String inputV;
		String inputYN;

		while (askAgainCV) {
			System.out.println("Please enter the column name!");
			inputC = ReadConsole();
			columnNames.add(inputC);
			System.out.println("Column Name Added!!!");
			System.out.println("--------------------");
			System.out.println();
			System.out.println();
			System.out.println("Please enter the variable name related to the column you entered!");
			inputV = ReadConsole();
			variableNames.add(inputV);
			System.out.println("Variable Name Added!!!");
			System.out.println("--------------------");
			System.out.println();
			System.out.println();
			System.out.println("Add one more Column? Y or N");
			inputYN = ReadConsole();
			if (inputYN.toUpperCase().equals("Y")) {
				askAgainCV = true;
			} else if (inputYN.toUpperCase().equals("N")) {
				askAgainCV = false;
			} else {
				askAgainCV = true;
				System.err.println("Please Type only Y or N!");
			}
		}
	}

	private static void askWhereClauseIdAndVariable() {
		System.out.println("Please enter the column name to put in WHERE Clause!");
		inputId = ReadConsole();
		System.out.println("Please enter the variable name related to the column you entered!");
		inputVari = ReadConsole();
	}

	private static void updatePrintOutput() {
		System.out.println("Here you go! Copy and Paste the following line to use!!!");
		System.out.println("-------------------");
		System.out.println();

		System.out.print("statement.executeUpdate(\"UPDATE " + tableName + " SET");
		int count = 0;
		int listSize = columnNames.size();
		for (String column : columnNames) {
			System.out.print(
					" " + column.toString() + " = \\\"\"" + " + " + variableNames.get(count).toString() + " + \"\\\"");
			if (count < listSize - 1)
				System.out.print(",");
			count++;
		}
		System.out.print(" WHERE " + inputId + " = \" + " + inputVari + ");");

	}

	private static void insertPrintOutput() {
		System.out.println();
		System.out.println();
		System.out.println("Here you go! Copy and Paste the following line to use!!!");
		System.out.println("-------------------");
		System.out.println();

		int count = 0;
		int listSize = columnNames.size();
		System.out.print("statement.executeUpdate(\"INSERT INTO " + tableName + "(");

		for (String column : columnNames) {
			System.out.print(column.toString());
			if (count < listSize - 1)
				System.out.print(", ");
			count++;
		}

		System.out.print(")");

		System.out.print(" VALUES (\\\"\" ");

		count = 0;
		for (String vari : variableNames) {
			System.out.print("+ " + vari.toString() + " + \"\\\"");
			if (count < listSize - 1)
				System.out.print(",\\\"\"");
			count++;
		}
		System.out.println(")\");");
	}

	public static String ReadConsole() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

}
