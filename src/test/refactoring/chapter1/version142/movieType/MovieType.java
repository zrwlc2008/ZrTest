package test.refactoring.chapter1.version142.movieType;

public abstract class MovieType {

	/**
	 * ��ȡӰƬ������
	 * 
	 * @return
	 */
	public abstract int getTypeCode();

	/**
	 * ���㻨��
	 * 
	 * @param daysRental
	 * @return
	 */
	public abstract double getCost(int daysRented);

	/**
	 * �������
	 * 
	 * @return
	 */
	public int getPoints(int daysRented) {
		// һ����r����+1
		return 1;
	}
}
