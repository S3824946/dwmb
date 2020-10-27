package dwmb;

import java.io.PrintWriter;

public class Expense extends MoneyItem {
	protected String category;

	public Expense(String[] file) {
		super(file);
		this.category = file[4];
	}

	public Expense(String frequency, double amount, String label, String category) {
		super(frequency, amount, label);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}


	@Override
	public void displayItem() {
		String type = this.getClass().getSimpleName();
		System.out.print("Type of Transaction: " + type);
		System.out.print("\nAmount: $" + amount);
		System.out.print("\nCategory: " + category);
		System.out.print("\nLabel: " + label);
		switch (frequency.toUpperCase()) {
		case "W":
			System.out.print("\nFrequency: Weekly");
			break;
		case "F":
			System.out.print("\nFrequency: Fortnightly");
			break;
		case "M":
			System.out.print("\nFrequency: Monthly");
			break;
		case "Q":
			System.out.print("\nFrequency: Quarterly");
			break;
		case "A":
			System.out.print("\nFrequency: Annually");
			break;
		}
	}

	// Will write out all data to a file
	@Override
	public void saveData(PrintWriter pw) {
		pw.print(this.getClass().getSimpleName() + ":");
		pw.print(frequency + ":");
		pw.print(amount + ":");
		pw.print(label + ":");
		pw.print(category + ":");
	}

}
