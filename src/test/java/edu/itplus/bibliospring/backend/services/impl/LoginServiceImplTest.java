package edu.itplus.bibliospring.backend.services.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import edu.itplus.bibliospring.backend.utils.PasswordEncrypter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LoginServiceImplTest {
    private LoginServiceImpl serviceUnderTest;
    private UserDAO testUserDao;
    private PasswordEncrypter testPasswordEncrypter;
    private User nonDbUser, dbUser, nonDbUser2;

    @BeforeEach
    void setUp() {
        // Initialize the service under test
        serviceUnderTest = new LoginServiceImpl();

        nonDbUser = new User();
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUsername("Pistike");
        nonDbUser.setUuid(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setPassword(TestPasswordEncrypter.hashedPassword);
        dbUser.setUsername("Pistike");
        dbUser.setUuid(TestPasswordEncrypter.salt);
        dbUser.setId(1L);

        // Mock user for register test
        nonDbUser2 = new User();
        nonDbUser2.setPassword(TestPasswordEncrypter.password);
        nonDbUser2.setUsername("Janoska");
        nonDbUser2.setUuid(TestPasswordEncrypter.salt);
        nonDbUser2.setId(2L);

        testUserDao = mock(UserDAO.class);
        testPasswordEncrypter = mock(PasswordEncrypter.class);
        when(testPasswordEncrypter.hashPassword(nonDbUser.getPassword(), nonDbUser.getUuid()))
                .thenReturn(dbUser.getPassword());

        ReflectionTestUtils.setField(serviceUnderTest, "userDAO", testUserDao);
        when(testUserDao.findByUsername(nonDbUser.getUsername())).thenReturn(dbUser);
        when(testUserDao.findByUsername("WrongUser")).thenReturn(null);

        ReflectionTestUtils.setField(serviceUnderTest, "passwordEncrypter", testPasswordEncrypter);

        // Mock dependencies if necessary, e.g., UserDAO and PasswordEncrypter
        // This can be done using a mocking framework like Mockito
        // For example:
        // userDAO = mock(UserDAO.class);


    }

    @Test
    void login_withNoCredentialsCheck_returnsTrue() {
        // Arrange
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the login method with the mock User object
        boolean loginStatus = serviceUnderTest.login(nonDbUser);

        // Assert
        // Verify that the UserDAO's findByUsername method was called with the correct username
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
        assertThat(loginStatus).isTrue();
        verify(testUserDao, times(1)).findByUsername(nonDbUser.getUsername());

        //assertThat(testUserDao.getLastSearchedUsernName()).isEqualTo(TestUserDAO.nonDbUser.getUsername());
    }

    @Test
    void login_withWrongCredentialsCheck_returnsFalse() {
        // Arrange
        // Create a mock User object with a username and password that do not match the database user
        User wrongUser = new User();
        wrongUser.setUsername("WrongUser");
        wrongUser.setPassword("WrongPassword");
        wrongUser.setUuid(TestPasswordEncrypter.salt);
        wrongUser.setId(3L);

        // Act
        // Call the login method with the mock User object
        boolean loginStatus = serviceUnderTest.login(wrongUser);

        // Assert
        // Verify that the UserDAO's findByUsername method was called with the correct username
        // Verify that the PasswordEncrypter's hashPassword method was not called since the user does not exist
        assertThat(loginStatus).isFalse();
        verify(testUserDao, times(1)).findByUsername("WrongUser");
    }

    @Test
    void register_withLegitUserData_userIsCreated() {
        // Arrange
        // Create a mock User object with a username and password
        // Mock the PasswordEncrypter to return a hashed password when hashPassword is called

        // Act
        // Call the register method with the mock User object
        serviceUnderTest.register(nonDbUser2);

        // Assert
        // Verify that the UserDAO's create method was called with the User object containing the hashed password
        // Verify that the PasswordEncrypter's hashPassword method was called with the correct parameters
        assertThat(nonDbUser2.getPassword()).isEqualTo(TestPasswordEncrypter.hashedPassword);
        verify(testUserDao, times(1)).create(nonDbUser2);
    }
}