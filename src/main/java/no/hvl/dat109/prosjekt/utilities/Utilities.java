package no.hvl.dat109.prosjekt.utilities;

import no.hvl.dat109.spring.beans.UsersBean;

import java.util.Random;

import static no.hvl.dat109.prosjekt.utilities.PasswordHasher.check;
import static no.hvl.dat109.prosjekt.utilities.PasswordHasher.getSaltedHash;

public class Utilities {

    private static final String numbers = "1234567890";
    private static final Random random = new Random();

    /**
     * Generate short passwords with a given length
     *
     * @param length length you want password to be
     * @return string of password
     */
    public static String generateShortPassword(int length) {
        //Create char array from letters and create a stringbuilder
        char[] numberArray = numbers.toCharArray();
        StringBuilder pass = new StringBuilder();

        //Loop through and add a random letter from the char array
        for (int i = 0; i < length; i++) {
            pass.append(numberArray[random.nextInt(numbers.length())]);
        }

        return pass.toString();
    }

    /**
     * Hash a given password with salt
     *
     * @param password password to hash
     * @return hashed password
     */
    public static String hashPassword(String password) {
        try {
            return getSaltedHash(password);
        } catch (Exception e) {
            System.err.println("Kunne ikke hashe passord!");
        }
        return "";
    }

    /**
     * Check password if it is the same
     *
     * @param password password to check
     * @param user     user with password to check
     * @return if passwords are the same
     */
    public static boolean checkPassword(String password, UsersBean user) {
        try {
            return check(password, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
