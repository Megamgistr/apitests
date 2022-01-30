package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";

    public static String getRandomText(int length) {
        String helpString = "A";
        String text = RandomStringUtils.random(length, ALPHA_NUMERIC_STRING.toCharArray());
        String textTrim = text.replaceAll("[\\s]{2,}", " ").trim();
        return textTrim.length() == text.length() ? textTrim : textTrim + new String(new char[text.length() - textTrim.length()]).replace("\0", helpString);
    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.random(length, false, true);
    }
}
