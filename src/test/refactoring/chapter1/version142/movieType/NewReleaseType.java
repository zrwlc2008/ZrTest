package test.refactoring.chapter1.version142.movieType;

import test.refactoring.chapter1.version142.Movie;

public class NewReleaseType extends MovieType {

	@Override
	public int getTypeCode() {
		return Movie.NEW_RELEASE;
	}

	@Override
	public double getCost(int daysRented) {
		// ��Ƭÿ��3Ԫ
		return daysRented * 3;
	}

	@Override
	public int getPoints(int daysRented) {
		// �������Ƭ�������1�����ϣ�����=2
		return (daysRented > 1) ? 2 : 1;
	}

}
