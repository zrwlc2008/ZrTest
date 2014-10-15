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
	 * ΪʲôҪ�����ڳ��ȴ���Movie�������ǽ�Movie����Rental������Ϊ��ϵͳ���ܻ����ӰƬ���ͣ�
	 * ���ӰƬ���������仯������Movie��֮���ܵõ�����
	 */
	/**
	 * ��ѯ���û���
	 * @return
	 */
	public double getCost(int daysRented) {
		double thisCost = 0;
		switch (this.getPriceCode()) {
		case Movie.REGULAR:
			// ��ͨƬ2��2Ԫ
			thisCost += 2;
			// ����2��ÿ��1.5Ԫ
			if (daysRented > 2) {
				thisCost += (daysRented - 2) * 1.5;
			}
			break;
		case Movie.NEW_RELEASE:
			// ��Ƭÿ��3Ԫ
			thisCost += daysRented * 3;
			break;
		case Movie.CHILDRENS:
			// ��Ƭ3��1.5Ԫ
			thisCost += 1.5;
			// ����3��ÿ��1.5Ԫ
			if (daysRented > 3) {
				thisCost += (daysRented - 3) * 1.5;
			}
			break;
		}
		return thisCost;
	}
}
