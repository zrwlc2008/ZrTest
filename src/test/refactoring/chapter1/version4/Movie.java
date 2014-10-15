package test.refactoring.chapter1.version4;

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

	/*
	 * 为什么要将租期长度传给Movie，而不是将Movie传给Rental对象？因为本系统可能会添加影片类型，
	 * 如果影片类型有所变化，就在Movie内之内能得到控制
	 */
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
			// 新片3天1.5元
			thisCost += 1.5;
			// 超过3天每天1.5元
			if (daysRented > 3) {
				thisCost += (daysRented - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}
}
