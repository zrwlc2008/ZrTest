package test.refactoring.chapter1.version2;

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

			// step4: ����Extract Method(110) �� Move Method(142)�����������Ƶ�Rental��
			totalPoints += each.getPoints();

			// �������Ƭ�������1�����ϣ������ټ�1
			// if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
			// each.getDaysRented() > 1) {
			// totalPoints++;
			// }

			// step3:thisCost����ö����ˣ�������each.getCost()��ִ�н����Ȼ��Ͳ����б仯��
			// ��������Replace Temp with Query(120)�����ȥ

			// double thisCost = each.getCost() ;

			// �ۼӻ���
			totalCost += each.getCost();

			// ��ʾӰƬ���ƺ�ӰƬ����
			result += "\t" + each.getMovie().getTitle() + "\t" + each.getCost() + "\n";

		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

	/**
	 * step1: �ҳ�������߼����Ų�����Extract Method(110)
	 * step2: �������ʹ��������Rental�����Ϣ��˿��û������Customer����Ϣ��
	 * �����������£�����Ӧ�÷�������ʹ�õ����ݵ����������ڣ����Դ˺���Ӧ���Ƶ�Rental�࣬����Move Method(142)
	 */
	
	// private double getCost(Rental rental) {
	// double thisCost = 0;
	// switch (rental.getMovie().getPriceCode()) {
	// case Movie.REGULAR:
	// // ��ͨƬ2��2Ԫ
	// thisCost += 2;
	// // ����2��ÿ��1.5Ԫ
	// if (rental.getDaysRented() > 2) {
	// thisCost += (rental.getDaysRented() - 2) * 1.5;
	// }
	// break;
	// case Movie.NEW_RELEASE:
	// // ��Ƭÿ��3Ԫ
	// thisCost += rental.getDaysRented() * 3;
	// break;
	// case Movie.CHILDRENS:
	// // ��Ƭ3��1.5Ԫ
	// thisCost += 1.5;
	// // ����3��ÿ��1.5Ԫ
	// if (rental.getDaysRented() > 3) {
	// thisCost += (rental.getDaysRented() - 3) * 1.5;
	// }
	// break;
	// }
	// return thisCost;
	// }
}
