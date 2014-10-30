package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class RegularType extends MovieType {
	
	@Override
	public int getTypeCode() {
		return Movie.REGULAR;
	}

	@Override
	public double getCost(int daysRented) {
		// 普通片2天内2元
		double result = 2;
		// 超过2天每天1.5元
		if (daysRented > 2) {
			result += (daysRented - 2) * 1.5;
		}
		return result;
	}


}
