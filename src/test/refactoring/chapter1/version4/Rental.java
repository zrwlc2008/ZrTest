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

	//step1:��ò�Ҫ����һ����������Ի���������switch��䣬������ò�ʹ�ã�ҲӦ���ڶ����Լ���������ʹ��
	//���Դ˷���Ӧ���Ƶ�Movie��
	/**
	 * ��ѯ���û���
	 * @return
	 */
	public double getCost() {
		return this.getMovie().getCost(this.getDaysRented());
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
