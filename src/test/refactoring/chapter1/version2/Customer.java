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

			// step4: 运用Extract Method(110) 和 Move Method(142)将积分运算移到Rental类
			totalPoints += each.getPoints();

			// 如果是新片并且租借1天以上，积分再加1
			// if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
			// each.getDaysRented() > 1) {
			// totalPoints++;
			// }

			// step3:thisCost如今变得多余了，它接受each.getCost()的执行结果，然后就不再有变化，
			// 可以运用Replace Temp with Query(120)将其除去

			// double thisCost = each.getCost() ;

			// 累加花费
			totalCost += each.getCost();

			// 显示影片名称和影片花费
			result += "\t" + each.getMovie().getTitle() + "\t" + each.getCost() + "\n";

		}
		// 显示总花费和总积分
		result += "Your totalCost is " + totalCost + "\n";
		result += "Your totalPoints is " + totalPoints;
		return result;
	}

	/**
	 * step1: 找出代码的逻辑泥团并运用Extract Method(110)
	 * step2: 这个函数使用了来自Rental类的信息，丝毫没有来自Customer的信息，
	 * 绝大多数情况下，函数应该放在它所使用的数据的所属对象内，所以此函数应该移到Rental类，运用Move Method(142)
	 */
	
	// private double getCost(Rental rental) {
	// double thisCost = 0;
	// switch (rental.getMovie().getPriceCode()) {
	// case Movie.REGULAR:
	// // 普通片2天2元
	// thisCost += 2;
	// // 超过2天每天1.5元
	// if (rental.getDaysRented() > 2) {
	// thisCost += (rental.getDaysRented() - 2) * 1.5;
	// }
	// break;
	// case Movie.NEW_RELEASE:
	// // 新片每天3元
	// thisCost += rental.getDaysRented() * 3;
	// break;
	// case Movie.CHILDRENS:
	// // 新片3天1.5元
	// thisCost += 1.5;
	// // 超过3天每天1.5元
	// if (rental.getDaysRented() > 3) {
	// thisCost += (rental.getDaysRented() - 3) * 1.5;
	// }
	// break;
	// }
	// return thisCost;
	// }
}
