package com.desafioey.apiusuarios.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){2,}).+$");

    private ValidationUtil() {
        // private constructor to prevent instantiation
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_REGEX.matcher(password).matches();
    }
}
