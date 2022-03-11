package com.school.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "numbers_base")
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status_of_number_available_to_connect")
    private boolean availableToConnectStatus;

    public Number() {
    }

    public Number(String phoneNumber, boolean availableToConnectStatus) {
        this.phoneNumber = phoneNumber;
        this.availableToConnectStatus = availableToConnectStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAvailableToConnectStatus() {
        return availableToConnectStatus;
    }

    public void setAvailableToConnectStatus(boolean availableToConnectStatus) {
        this.availableToConnectStatus = availableToConnectStatus;
    }
}
