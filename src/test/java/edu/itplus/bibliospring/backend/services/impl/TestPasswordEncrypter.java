package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.utils.PasswordEncrypter;

public class TestPasswordEncrypter implements PasswordEncrypter {
    @Override
    public String hashPassword(String password, String salt) {
        return "";
    }
}
