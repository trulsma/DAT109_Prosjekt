package no.hvl.dat109.prosjekt;

import java.util.Random;

public class Utilities {

    private static final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final Random random = new Random();

    /**
     * Generate short passwords with a given length
     * @param length length you want password to be
     * @return string of password
     */
    public static String generateShortPassword(int length) {
        //Create char array from letters and create a stringbuilder
        char[] letterArray = letters.toCharArray();
        StringBuilder pass = new StringBuilder();

        //Loop through and add a random letter from the char array
        for (int i = 0; i < length; i++) {
            pass.append(letterArray[random.nextInt(letters.length())]);
        }

        return pass.toString();
    }
}
