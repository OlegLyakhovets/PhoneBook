package com.project.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
}
