package edu.itplus.bibliospring.backend.utils;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

class PasswordEncrypterTest {
    @Test
    void hashPassword_passwordIsHashed_hashedPasswordIsCorrect() {
        // Arrange
        String password = "password123";
        String salt = UUID.randomUUID().toString();
        PasswordEncrypter systemUnderTest = new PasswordEncrypter();

        // Act
        String hashedPassword = systemUnderTest.hashPassword(password, salt);

        // Assert
        String expectedHash = "D0E4735D4B1595FA719032A1DC05A26B23CBCD876EDD6DB7A5F4496BF13488A5";

        assertThat(hashedPassword).isEqualTo(expectedHash);
    }
}