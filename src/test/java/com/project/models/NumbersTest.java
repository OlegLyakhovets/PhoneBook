package com.project.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */
class NumbersTest {

    Numbers number = new Numbers();

    @Test
    void getNumber() {
        number.setNumber("+1112223344");
        assertEquals("+1112223344", number.getNumber());
    }

    @Test
    void setNumber() {
        number = mock(Numbers.class);
        doNothing().when(number).setNumber(isA(String.class));
        number.setNumber("+1112223344");
        verify(number, times(1)).setNumber("+1112223344");
    }

    @Test
    void testEquals() {
        number.setNumber("+1112223344");
        Numbers numbToCompare = new Numbers("+1112223344");
        assertTrue(number.equals(numbToCompare));
    }
}