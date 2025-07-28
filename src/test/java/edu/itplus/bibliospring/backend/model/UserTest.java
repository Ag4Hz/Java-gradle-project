package edu.itplus.bibliospring.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testEquals_usersAreEqual_returnsTrue() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testUser");
        user1.setPassword("testPass");

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("testUser");
        user2.setPassword("testPass");
        user2.setUuid(user1.getUuid());

        assertEquals(user1, user2, "Users with same uuid should be equal");
    }

    @Test
    void testEquals_usersAreNotEqual_returnsFalse() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testUser");
        user1.setPassword("testPass");

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("testUser");
        user2.setPassword("testPass");

        assertNotEquals(user1, user2, "Users with different uuids should not be equal");
    }
}