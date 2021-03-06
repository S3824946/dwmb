package dwmb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class dudewhere {
	private static int MAX_TRANS = 100;
	private static MoneyItem[] budget = new MoneyItem[MAX_TRANS];
	private static int numTransactions = 0;

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String selection;
		loadData();
		do {
			System.out.println("Dude, Where's my Budget?");
			System.out.println("Select from the following options:");
			System.out.println("A) Add Income");
			System.out.println("B) Add Expense");
			System.out.println("C) View Totals");
			System.out.println("X) Save and Exit");

			selection = sc.nextLine();

			switch (selection.toUpperCase()) {
			case "A":
				addIncome();
				break;
			case "B":
				addExpense();
				break;
			case "C":
				viewTotals();
				break;
			case "X":
				System.out.print("Saving and exiting...");
				break;
			default:
				System.out.print("Error - Invalid selection!");
			}
		} while (!selection.equalsIgnoreCase("X"));
		saveData();
		sc.close();
	}

	private static void loadData() {
		try {
			Scanner fileScanner = new Scanner(new FileReader("budget.txt"));
			int lineCount = 0;
			while (fileScanner.hasNextLine()) {
				String[] line = fileScanner.nextLine().split(":");
				if (line[0].contentEquals("Income")) {
					budget[numTransactions] = new Income(line);
					numTransactions++;
				} else if (line[0].contentEquals("Expense")) {
					budget[numTransactions] = new Expense(line);
					numTransactions++;
				} else {
					System.out.print("Line " + lineCount + " does not contain valid data!");
				}
			}
		} catch (IOException e) {

		}
	}

	private static void saveData() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("budget.txt"));
			for (int i = 0; i < numTransactions; i++) {
				budget[i].saveData(pw);
			}
			pw.close();
		} catch (IOException e) {
			System.out.print("Error - Could not write to file budget.txt");
		}

	}

	private static void addIncome() {
		System.out.println("Enter income amount:");
		double amount = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter frequency of income:");
		System.out.println("(W for Weekly, F for Fortnightly, M for Monthly, Q for Quarterly, A for Annually)");
		String frequency = sc.nextLine();
		System.out.println("Enter a label for this income:");
		System.out.println("Work, Centrelink, etc.");
		String label = sc.nextLine();
		budget[numTransactions] = new Income(frequency, amount, label);
		numTransactions++;
	}

	private static void addExpense() {
		System.out.println("Enter expense amount:");
		double amount = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter frequency of expense:");
		System.out.println("(W for Weekly, F for Fortnightly, M for Monthly, Q for Quarterly, A for Annually)");
		String frequency = sc.nextLine();
		System.out.println("Enter a category for this expense:");
		System.out.println("(Financial, Utilities, Property, etc");
		String category = sc.nextLine();
		System.out.println("Enter a label for this expense:");
		System.out.println("(phone bill, food, fuel, etc.)");
		String label = sc.nextLine();
		budget[numTransactions] = new Expense(frequency, amount, label, category);
		numTransactions++;
	}

	private static void viewTotals() {
		double inTotal = 0;
		double exTotal = 0;
		double remaining = 0;
		System.out.println("What frequency would you like the totals to be shown in?");
		System.out.println("(W for Weekly, F for Fortnightly, M for Monthly, Q for Quarterly, A for Annually)");
		String freqOut = sc.nextLine();
		int freqOutNum = freqCheck(freqOut);
		for (int i = 0; i < numTransactions; i++) {
			budget[i].displayItem();
			String freqIn = budget[i].getFreq();
			int freqInNum = freqCheck(freqIn);
			double freqCalc = freqCalc(freqInNum, freqOutNum, budget[i].getAmt());
			if (budget[i].getType().contentEquals("Income")) {
				inTotal += freqCalc;
			} else if (budget[i].getType().contentEquals("Expense")) {
				exTotal += freqCalc;
			}
		}
		remaining = inTotal - exTotal;
		System.out.println("Total income: $" + String.format("%.2f", inTotal));
		System.out.println("Total Expenses: $" + String.format("%.2f", exTotal));
		System.out.println("Remaining after expenses: $" + String.format("%.2f", remaining));
	}

	private static int freqCheck(String in) {
		int result = 1;
		switch (in.toUpperCase()) {
		case "W":
			result = 52;
			return result;
		case "F":
			result = 26;
			return result;
		case "M":
			result = 12;
			return result;
		case "Q":
			result = 4;
			return result;
		case "A":
			result = 1;
			return result;
		default:
			return result;
		}
	}

	private static double freqCalc(int freqIn, int freqOut, double amount) {
		amount = (amount * freqIn);
		amount = (amount / freqOut);
		return amount;
	}
}
