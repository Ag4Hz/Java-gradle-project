package edu.itplus.bibliospring.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class LoginServiceImplTest {
    private LoginServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void login() {
        // Arrange
        // Create a mock User object with a username and password
        // Mock the UserDAO to return a User object when findByUsername is called
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the login method with the mock User object

        // Assert
        // Verify that the returned value matches the expected result (true or false)
        // Verify that the UserDAO's findByUsername method was called with the correct username
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
    }

    @Test
    void register() {
        // Arrange
        // Create a mock User object with a username and password
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the register method with the mock User object

        // Assert
        // Verify that the UserDAO's create method was called with the User object containing the hashed password
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
    }
}