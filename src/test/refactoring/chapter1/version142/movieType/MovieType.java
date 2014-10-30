package test.refactoring.chapter1.version142.movieType;

public abstract class MovieType {

	/**
	 * 获取影片类型码
	 * 
	 * @return
	 */
	public abstract int getTypeCode();

	/**
	 * 计算花费
	 * 
	 * @param daysRental
	 * @return
	 */
	public abstract double getCost(int daysRented);

	/**
	 * 计算积分
	 * 
	 * @return
	 */
	public int getPoints(int daysRented) {
		// 一般情況积分+1
		return 1;
	}
}
