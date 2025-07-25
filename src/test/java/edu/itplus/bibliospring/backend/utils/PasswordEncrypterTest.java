package edu.itplus.bibliospring.backend.utils;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

class PasswordEncrypterTest {
    @Test
    void hashPassword_passwordIsHashed_hashedPasswordIsCorrect() {
        // Arrange
        String password = "password123";
        String salt = "18b09ff8-9718-49ea-9588-c1a65ff10d8e";
        PasswordEncrypter systemUnderTest = new PasswordEncrypter();

        // Act
        String hashedPassword = systemUnderTest.hashPassword(password, salt);

        // Assert
        String expectedHash = "7DA449FC9ECCB29FF0312FFF1F88C125B38B2F1EE5C37DD52B52A1EE9593883B";
        assertThat(hashedPassword).isEqualTo(expectedHash);
    }
}