package utils;

import java.util.Random;

public class HelperMethods {
	public static String generateRandomString(int length) {
		String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZacbdefghijklmnopqrstuwxyz";
		Random rnd = new Random();
		StringBuilder randomString = new StringBuilder(Alphabet.length());
		for (int i = 0; i < length; i++)
			randomString.append(Alphabet.charAt(rnd.nextInt(Alphabet.length())));
		return randomString.toString();
	}
}
