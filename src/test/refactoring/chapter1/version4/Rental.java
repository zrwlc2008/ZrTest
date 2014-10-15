package test.refactoring.chapter1.version4;

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

	//step1:最好不要在另一个对象的属性基础上运用switch语句，如果不得不使用，也应该在对象自己的数据上使用
	//所以此方法应该移到Movie类
	/**
	 * 查询租用花费
	 * @return
	 */
	public double getCost() {
		return this.getMovie().getCost(this.getDaysRented());
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
