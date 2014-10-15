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
	 * 显示租片情况
	 * 
	 * @return
	 */
	public String statment() {

		// step1:临时变量可能是个问题，他们只在自己所属的函数中有效，所以他们会助长冗长而复杂的函数
		// 运用Replace Temp with Query(120)，利用查询函数来取代totalCost和totalPoints
		// 总花费
		// double totalCost = 0;

		// 积分
		// int totalPoints = 0;

		String result = "Rental For " + getName() + "\n";

		// step2: 这里变成了只是显示影片名称和租用花费
		for (Rental rental : rentals) {

			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCost() + "\n";

		}
		// 显示总花费和总积分
		result += "Your totalCost is " + getTotalCost() + "\n";
		result += "Your totalPoints is " + getTotalPoints();
		return result;
	}

	/**
	 * 查询总花费
	 */
	private int getTotalCost() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getCost();

		}
		return result;
	}

	/**
	 * 查询总积分
	 */
	private int getTotalPoints() {
		int result = 0;
		for (Rental each : rentals) {

			result += each.getPoints();

		}
		return result;
	}
}
