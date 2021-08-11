package com.project.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidateTest {

    @Test
    void nameValidateTrue() {
        assertTrue(UserValidate.nameValidate("Donald"));
    }
    @Test
    void nameValidateFalse() {
        assertFalse(UserValidate.nameValidate("3Donald5"));
    }

    @Test
    void emailValidateTrue() {
        assertTrue(UserValidate.emailValidate("trump@gmail.com"));
    }
    @Test
    void emailValidateFalse() {
        assertFalse(UserValidate.emailValidate("trump_gmail.com"));
    }

    @Test
    void numberValidateTrue() {
        assertTrue(UserValidate.numberValidate("+0009998877"));
    }

    @Test
    void numberValidateFalse() {
        assertFalse(UserValidate.numberValidate("ABC009998877"));
    }
}