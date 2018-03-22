package examples;

import java.util.Random;

public class TwoChoices {
	public static void main(String[] args) {
		Random random = new Random();
		if (random.nextBoolean()) {
			if (random.nextBoolean()) {
				System.out.println("1");
			} else {
				System.out.println("2");
			}
		} else {
			if (random.nextBoolean()) {
				System.out.println("3");
			} else {
				System.out.println("4");
			}
		}
	}
}