package dwmb;

import java.io.PrintWriter;

public abstract class MoneyItem {
	protected String frequency;
	protected double amount;
	protected String label;

	// Constructor for data from file
	public MoneyItem(String[] file) {
		this.frequency = file[1];
		this.amount = Double.parseDouble(file[2]);
		this.label = file[3];

	}

	// Constructor for new Object from main program
	public MoneyItem(String frequency, double amount, String label) {
		this.frequency = frequency;
		this.amount = amount;
		this.label = label;

	}

	// Getters below here
	public String getFreq() {
		return frequency;
	}

	public double getAmt() {
		return amount;
	}

	public String getType() {
		String type = this.getClass().getSimpleName();
		return type;
	}

	public String getLabel() {
		return label;
	}

	// displays details of entire item
	public void displayItem() {
		String type = this.getClass().getSimpleName();
		System.out.println("Type of Transaction: " + type);
		System.out.println("Amount: $" + String.format("%.2f", amount));
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
	public void saveData(PrintWriter pw) {
		pw.print(this.getClass().getSimpleName() + ":");
		pw.print(frequency + ":");
		pw.print(amount + ":");
		pw.print(label + "\n");
	}
}
