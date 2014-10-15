package test.refactoring.chapter1.version3;

public class Rental {
	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	/**
	 * 查询租用花费
	 * @return
	 */
	public double getCost() {
		double thisCost = 0;
		switch (this.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			// 普通片2天2元
			thisCost += 2;
			// 超过2天每天1.5元
			if (this.getDaysRented() > 2) {
				thisCost += (this.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// 新片每天3元
			thisCost += this.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			// 新片3天1.5元
			thisCost += 1.5;
			// 超过3天每天1.5元
			if (this.getDaysRented() > 3) {
				thisCost += (this.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}

	public int getPoints() {
		// 如果是新片并且租借1天以上，积分=2
		if (this.getMovie().getPriceCode() == Movie.NEW_RELEASE && this.getDaysRented() > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
