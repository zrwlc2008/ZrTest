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
	 * 显示租片情况
	 * 
	 * @return
	 */
	public String statment() {
		// 总花费
		double totalCost = 0;
		// 积分
		int totalPoints = 0;
		String result = "Rental For " + getName() + "\n";

		// 累计花费和积分
		for (Rental each : rentals) {
			double thisCost = 0;

			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				// 普通片2天2元
				thisCost += 2;
				// 超过2天每天1.5元
				if (each.getDaysRented() > 2) {
					thisCost += (each.getDaysRented() - 2) * 1.5;
				}
				break;
			case Movie.NEW_RELEASE:
				// 新片每天3元
				thisCost += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				// 新片3天1.5元
				thisCost += 1.5;
				// 超过3天每天1.5元
				if (each.getDaysRented() > 3) {
					thisCost += (each.getDaysRented() - 3) * 1.5;
				}
				break;
			}

			// 积分加1
			totalPoints++;
			// 如果是新片并且租借1天以上，积分再加1
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
				totalPoints++;
			}

			// 显示片名和当前花费
			result += "\t" + each.getMovie().getTitle() + "\t" + thisCost + "\n";

			// 累加花费
			totalCost += thisCost;
		}
		// 显示总花费和总积分
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}
}
