package test.refactoring.chapter1.version4;

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

		// step1:��ʱ���������Ǹ����⣬����ֻ���Լ������ĺ�������Ч���������ǻ������߳������ӵĺ���
		// ����Replace Temp with Query(120)�����ò�ѯ������ȡ��totalCost��totalPoints
		// �ܻ���
		// double totalCost = 0;

		// ����
		// int totalPoints = 0;

		String result = "Rental For " + getName() + "\n";

		// step2: ��������ֻ����ʾӰƬ���ƺ����û���
		for (Rental rental : rentals) {

			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCost() + "\n";

		}
		// ��ʾ�ܻ��Ѻ��ܻ���
		result += "Your totalCost is " + getTotalCost() + "\n";
		result += "Your totalPoints is " + getTotalPoints();
		return result;
	}

	/**
	 * ��ѯ�ܻ���
	 */
	private int getTotalCost() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getCost();

		}
		return result;
	}

	/**
	 * ��ѯ�ܻ���
	 */
	private int getTotalPoints() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getPoints();

		}
		return result;
	}
}
