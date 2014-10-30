package test.refactoring.chapter1.version142;

import test.refactoring.chapter1.version142.movieType.ChildrenType;
import test.refactoring.chapter1.version142.movieType.MovieType;
import test.refactoring.chapter1.version142.movieType.NewReleaseType;
import test.refactoring.chapter1.version142.movieType.RegularType;

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private String title;
	private MovieType movieType;

	public Movie(String title, int priceCode) {
		this.title = title;
		setPriceCode(priceCode);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPriceCode() {
		return movieType.getTypeCode();
	}

	public void setPriceCode(int priceCode) {
		switch (priceCode) {
		case Movie.REGULAR:
			movieType = new RegularType();
			break;
		case Movie.NEW_RELEASE:
			movieType = new NewReleaseType();
			break;
		case Movie.CHILDRENS:
			movieType = new ChildrenType();
			break;
		default:
			movieType = new RegularType();
			break;
		}
	}

	/**
	 * 查询租用花费
	 * 
	 * @return
	 */
	public double getCost(int daysRented) {
		return movieType.getCost(daysRented);
	}

	/**
	 * 获取积分
	 * 
	 * @return
	 */
	public int getPoints(int daysRented) {
		return movieType.getPoints(daysRented);
	}
}
