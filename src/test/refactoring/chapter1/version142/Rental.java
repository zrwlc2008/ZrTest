package test.refactoring.chapter1.version142;

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
	 * ��ȡ����
	 * 
	 * @return
	 */
	public double getCost() {
		return this.getMovie().getCost(this.getDaysRented());
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public int getPoints() {
		return this.getMovie().getPoints(this.getDaysRented());
	}
}
