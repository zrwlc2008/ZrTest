package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class NewReleaseType extends MovieType {

	@Override
	public int getTypeCode() {
		return Movie.NEW_RELEASE;
	}

	@Override
	public double getCost(int daysRented) {
		// 新片每天3元
		return daysRented * 3;
	}

	@Override
	public int getPoints(int daysRented) {
		// 如果是新片并且租借1天以上，积分=2
		return (daysRented > 1) ? 2 : 1;
	}

}
