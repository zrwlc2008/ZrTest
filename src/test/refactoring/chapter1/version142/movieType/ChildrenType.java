package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class ChildrenType extends MovieType {

	@Override
	public int getTypeCode() {
		return Movie.CHILDRENS;
	}

	@Override
	public double getCost(int daysRented) {
		// ��ͯƬ3��1.5Ԫ
		double result = 1.5;
		// ����3��ÿ��1.5Ԫ
		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;

	}

}
