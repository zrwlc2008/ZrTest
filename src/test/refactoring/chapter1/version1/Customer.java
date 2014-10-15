package test.refactoring.chapter1.version1;

import java.util.Vector;

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
		String result = "Rental For " + getName() + "\n";

		// �ۼƻ��Ѻͻ���
		for (Rental each : rentals) {
			double thisCost = 0;

			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				// ��ͨƬ2��2Ԫ
				thisCost += 2;
				// ����2��ÿ��1.5Ԫ
				if (each.getDaysRented() > 2) {
					thisCost += (each.getDaysRented() - 2) * 1.5;
				}
				break;
			case Movie.NEW_RELEASE:
				// ��Ƭÿ��3Ԫ
				thisCost += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				// ��Ƭ3��1.5Ԫ
				thisCost += 1.5;
				// ����3��ÿ��1.5Ԫ
				if (each.getDaysRented() > 3) {
					thisCost += (each.getDaysRented() - 3) * 1.5;
				}
				break;
			}

			// ���ּ�1
			totalPoints++;
			// �������Ƭ�������1�����ϣ������ټ�1
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
				totalPoints++;
			}

			// ��ʾƬ���͵�ǰ����
			result += "\t" + each.getMovie().getTitle() + "\t" + thisCost + "\n";

			// �ۼӻ���
			totalCost += thisCost;
		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}
}
