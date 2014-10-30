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
	 * 显示租片情况
	 * 
	 * @return
	 */
	public String statment() {
		// 总花费
		double totalCost = 0;
		// 积分
		int totalPoints = 0;

		// 显示客户名称
		String result = "Rental For " + getName() + "\n";

		// 累计花费和积分
		for (Rental each : rentals) {

			totalPoints += each.getPoints();

			// 累加花费
			totalCost += each.getCost();

			// 显示影片名称和影片花费
			result += "\t" + each.getMovie().getTitle() + "\t" + each.getCost()
					+ "\n";

		}
		// 显示总花费和总积分
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

}
