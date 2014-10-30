package test.refactoring.chapter1.version141;

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	/**
	 * 查询租用花费
	 * @return
	 */
	public double getCost(int daysRented) {
		double thisCost = 0;
		switch (this.getPriceCode()) {
		case Movie.REGULAR:
			// 普通片2天2元
			thisCost += 2;
			// 超过2天每天1.5元
			if (daysRented > 2) {
				thisCost += (daysRented - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// 新片每天3元
			thisCost += daysRented * 3;
			break;
		case Movie.CHILDRENS:
			// 儿童片3天1.5元
			thisCost += 1.5;
			// 超过3天每天1.5元
			if (daysRented > 3) {
				thisCost += (daysRented - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}
	
	/**
	 * 获取积分
	 * 
	 * @return
	 */
	public int getPoints(int daysRented) {
		// 如果是新片并且租借1天以上，积分=2
		if (this.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
