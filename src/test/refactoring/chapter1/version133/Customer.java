package test.refactoring.chapter1.version133;

import java.util.Vector;

import test.refactoring.chapter1.version132.Rental;

public class Customer {
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		this.rentals.add(arg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ʾ��Ƭ���
	 * 
	 * @return
	 */
	public String statment() {

		// ��ʾ�ͻ�����
		String result = "Rental For " + getName() + "\n";

		for (Rental rental : rentals) {

			// ��ʾӰƬ���ƺ����û���
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ rental.getCost() + "\n";

		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + getTotalCost() + "\n";
		result += "Your totalPoints is " + getTotalPoints();
		return result;
	}

	/**
	 * ��ȡ�ܻ���
	 */
	private int getTotalCost() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getCost();

		}
		return result;
	}

	/**
	 * ��ȡ�ܻ���
	 */
	private int getTotalPoints() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getPoints();

		}
		return result;
	}
}
