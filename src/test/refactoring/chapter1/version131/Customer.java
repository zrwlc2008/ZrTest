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

			double thisCost = this.getCost(each);

			// 积分加1
			totalPoints++;
			// 如果是新片并且租借1天以上，积分再加1
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
					&& each.getDaysRented() > 1) {
				totalPoints++;
			}

			// 显示片名和当前花费
			result += "\t" + each.getMovie().getTitle() + "\t" + thisCost
					+ "\n";

			// 累加花费
			totalCost += thisCost;

		}
		// 显示总花费和总积分
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

	/**
	 * 获取花费
	 * 
	 * @param rental
	 * @return
	 */
	private double getCost(Rental rental) {
		double thisCost = 0;
		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			// 普通片2天2元
			thisCost += 2;
			// 超过2天每天1.5元
			if (rental.getDaysRented() > 2) {
				thisCost += (rental.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// 新片每天3元
			thisCost += rental.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			// 儿童片3天1.5元
			thisCost += 1.5;
			// 超过3天每天1.5元
			if (rental.getDaysRented() > 3) {
				thisCost += (rental.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}
}
