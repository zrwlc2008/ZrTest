package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class RegularType extends MovieType {
	
	@Override
	public int getTypeCode() {
		return Movie.REGULAR;
	}

	@Override
	public double getCost(int daysRented) {
		// ��ͨƬ2����2Ԫ
		double result = 2;
		// ����2��ÿ��1.5Ԫ
		if (daysRented > 2) {
			result += (daysRented - 2) * 1.5;
		}
		return result;
	}


}
