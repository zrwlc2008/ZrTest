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
	 * 显示租片情况
	 * 
	 * @return
	 */
	public String statment() {

		// 显示客户名称
		String result = "Rental For " + getName() + "\n";

		for (Rental rental : rentals) {

			// 显示影片名称和租用花费
			result += "\t" + rental.getMovie().getTitle() + "\t"
					+ rental.getCost() + "\n";

		}
		// 显示总花费和总积分
		result += "Your totalCost is " + getTotalCost() + "\n";
		result += "Your totalPoints is " + getTotalPoints();
		return result;
	}

	/**
	 * 获取总花费
	 */
	private int getTotalCost() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getCost();

		}
		return result;
	}

	/**
	 * 获取总积分
	 */
	private int getTotalPoints() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getPoints();

		}
		return result;
	}
}
