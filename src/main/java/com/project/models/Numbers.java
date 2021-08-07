package com.project.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Numbers {
    @Column(name="number")
    private String number;

    public Numbers() {
    }

    public Numbers(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Numbers)) return false;
        Numbers numbers = (Numbers) o;
        return Objects.equals(number, numbers.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
