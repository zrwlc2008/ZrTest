package test.refactoring.chapter1.version131;

import java.util.Vector;

import test.refactoring.chapter1.version1.Movie;
import test.refactoring.chapter1.version1.Rental;

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

			double thisCost = this.getCost(each);

			// ���ּ�1
			totalPoints++;
			// �������Ƭ�������1�����ϣ������ټ�1
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
					&& each.getDaysRented() > 1) {
				totalPoints++;
			}

			// ��ʾƬ���͵�ǰ����
			result += "\t" + each.getMovie().getTitle() + "\t" + thisCost
					+ "\n";

			// �ۼӻ���
			totalCost += thisCost;

		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param rental
	 * @return
	 */
	private double getCost(Rental rental) {
		double thisCost = 0;
		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			// ��ͨƬ2��2Ԫ
			thisCost += 2;
			// ����2��ÿ��1.5Ԫ
			if (rental.getDaysRented() > 2) {
				thisCost += (rental.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// ��Ƭÿ��3Ԫ
			thisCost += rental.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			// ��ͯƬ3��1.5Ԫ
			thisCost += 1.5;
			// ����3��ÿ��1.5Ԫ
			if (rental.getDaysRented() > 3) {
				thisCost += (rental.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}
}
