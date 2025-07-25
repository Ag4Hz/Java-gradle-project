package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.utils.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {
    public static final String password = "123";
    public static final String salt = "salt";
    public static final String hashedPassword = "hashed123";

    @Override
    public String hashPassword(String password, String salt) {
        if (password.equals(TestPasswordEncrypter.password)
                && salt.equals(TestPasswordEncrypter.salt)) {
            return hashedPassword;
        } else throw new IllegalArgumentException("Invalid password or salt");
    }
}
