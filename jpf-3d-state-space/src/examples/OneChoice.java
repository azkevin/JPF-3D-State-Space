package examples;

import java.util.Random;

public class OneChoice {
	public static void main(String[] args) {
		Random random = new Random();
		if (random.nextBoolean()) {
			System.out.println("1");
		} else {
			System.out.println("2");
		}
	}
}