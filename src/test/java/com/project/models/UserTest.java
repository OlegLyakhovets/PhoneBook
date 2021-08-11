package com.project.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */

class UserTest {

    User user;

    @BeforeEach
    void beforeEach() {
        user = new User();
    }

    @Test
    void getId() {
        user.setId(1l);
        assertEquals(1l, user.getId());
    }

    @Test
    void setId() {
        user = mock(User.class);
        doNothing().when(user).setId(isA(Long.class));
        user.setId(1l);
        verify(user, times(1)).setId(1l);

    }

    @Test
    void getFirstName() {
        user.setFirstName("Donald");
        assertEquals("Donald", user.getFirstName());
    }

    @Test
    void setFirstName() {
        user = mock(User.class);
        doNothing().when(user).setFirstName(isA(String.class));
        user.setFirstName("Donald");
        verify(user, times(1)).setFirstName("Donald");
    }

    @Test
    void getLastName() {
        user.setLastName("Trump");
        assertEquals("Trump", user.getLastName());
    }

    @Test
    void setLastName() {
        user = mock(User.class);
        doNothing().when(user).setLastName(isA(String.class));
        user.setLastName("Trump");
        verify(user, times(1)).setLastName("Trump");
    }

    @Test
    void getNumbers() {
        user.setNumber("+9990008877");
        assertTrue(user.getNumbers().contains(new Numbers("+9990008877")));
    }

    @Test
    void setNumber() {
        user = mock(User.class);
        doNothing().when(user).setNumber(isA(String.class));
        user.setNumber("+380998887766");
        verify(user, times(1)).setNumber("+380998887766");
    }

    @Test
    void getEmail() {
        user.setEmail("trump@gmail.com");
        assertEquals("trump@gmail.com", user.getEmail());
    }

    @Test
    void setEmail() {
        user = mock(User.class);
        doNothing().when(user).setEmail(isA(String.class));
        user.setEmail("test@gmail.com");
        verify(user, times(1)).setEmail("test@gmail.com");
    }

    @Test
    void getAddress() {
        user.setAddress("USA, CA");
        assertEquals("USA, CA", user.getAddress());
    }

    @Test
    void setAddress() {
        user = mock(User.class);
        doNothing().when(user).setAddress(isA(String.class));
        user.setAddress("USA, CA, San Francisco");
        verify(user, times(1)).setAddress("USA, CA, San Francisco");
    }

    @Test
    void compareTo() {
        User userToCompare = new User();
        user.setLastName("Trump");
        userToCompare.setLastName("Trump");
        assertEquals(0, user.compareTo(userToCompare) );
    }
}