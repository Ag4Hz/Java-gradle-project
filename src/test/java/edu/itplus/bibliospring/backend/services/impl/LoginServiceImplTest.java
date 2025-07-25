package edu.itplus.bibliospring.backend.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LoginServiceImplTest {
    private LoginServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp() {
        // Initialize the service under test
        serviceUnderTest = new LoginServiceImpl();
        ReflectionTestUtils.setField(serviceUnderTest, "userDAO", new TestUserDAO());
        ReflectionTestUtils.setField(serviceUnderTest, "passwordEncrypter", new TestPasswordEncrypter());

        // Mock dependencies if necessary, e.g., UserDAO and PasswordEncrypter
        // This can be done using a mocking framework like Mockito
        // For example:
        // userDAO = mock(UserDAO.class);
        // passwordEncrypter = mock(PasswordEncrypter.class);
        // serviceUnderTest.setUserDAO(userDAO);
        // serviceUnderTest.setPasswordEncrypter(passwordEncrypter);


    }

    @Test
    void login() {
        // Arrange
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the login method with the mock User object
        boolean loginStatus = serviceUnderTest.login(TestUserDAO.nonDbUser);

        // Assert
        // Verify that the returned value matches the expected result (true or false)
        // Verify that the UserDAO's findByUsername method was called with the correct username
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
        assertThat(loginStatus).isTrue();
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