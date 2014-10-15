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
	 * ��ѯ���û���
	 * @return
	 */
	public double getCost() {
		double thisCost = 0;
		switch (this.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			// ��ͨƬ2��2Ԫ
			thisCost += 2;
			// ����2��ÿ��1.5Ԫ
			if (this.getDaysRented() > 2) {
				thisCost += (this.getDaysRented() - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// ��Ƭÿ��3Ԫ
			thisCost += this.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			// ��Ƭ3��1.5Ԫ
			thisCost += 1.5;
			// ����3��ÿ��1.5Ԫ
			if (this.getDaysRented() > 3) {
				thisCost += (this.getDaysRented() - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}

	public int getPoints() {
		// �������Ƭ�������1�����ϣ�����=2
		if (this.getMovie().getPriceCode() == Movie.NEW_RELEASE && this.getDaysRented() > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}
