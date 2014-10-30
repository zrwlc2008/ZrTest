package test.refactoring.chapter1.version132;

import java.util.Vector;

import test.refactoring.chapter1.version1.Movie;

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
		// �ܻ���
		double totalCost = 0;
		// ����
		int totalPoints = 0;

		// ��ʾ�ͻ�����
		String result = "Rental For " + getName() + "\n";

		// �ۼƻ��Ѻͻ���
		for (Rental each : rentals) {

			totalPoints += each.getPoints();

			// �ۼӻ���
			totalCost += each.getCost();

			// ��ʾӰƬ���ƺ�ӰƬ����
			result += "\t" + each.getMovie().getTitle() + "\t" + each.getCost()
					+ "\n";

		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

}
