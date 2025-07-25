package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.utils.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {
    @Override
    public String hashPassword(String password, String salt) {
        if (password.equals("123") && salt.equals("salt")) {
            return "hashed123";
        } else throw new IllegalArgumentException("Invalid password or salt");
    }
}
