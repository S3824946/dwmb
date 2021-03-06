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
		System.out.println("Type of Transaction: " + type);
		System.out.println("Amount: $" + amount);
		System.out.println("Category: " + category);
		System.out.println("Label: " + label);
		switch (frequency.toUpperCase()) {
		case "W":
			System.out.println("Frequency: Weekly");
			break;
		case "F":
			System.out.println("Frequency: Fortnightly");
			break;
		case "M":
			System.out.println("Frequency: Monthly");
			break;
		case "Q":
			System.out.println("Frequency: Quarterly");
			break;
		case "A":
			System.out.println("Frequency: Annually");
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
		pw.print(category + "\n");
	}

}
