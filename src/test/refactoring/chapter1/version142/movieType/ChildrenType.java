package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class ChildrenType extends MovieType {

	@Override
	public int getTypeCode() {
		return Movie.CHILDRENS;
	}

	@Override
	public double getCost(int daysRented) {
		// 儿童片3天1.5元
		double result = 1.5;
		// 超过3天每天1.5元
		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;

	}

}
