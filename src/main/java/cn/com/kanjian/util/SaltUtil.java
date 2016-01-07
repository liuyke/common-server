package cn.com.kanjian.util;

import java.util.Random;

public class SaltUtil {

	private static String[] WORDS = new String[] { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9"
	};

	public static String randomSalt(int size) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(WORDS[random.nextInt(WORDS.length)]);
		}
		return sb.toString();
	}
	
	public static int randon6Num() {
		return new Random().nextInt(899999) + 100000;
	}
	
}
