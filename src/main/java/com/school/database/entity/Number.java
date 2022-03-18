package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "numbers_base")
@NoArgsConstructor
@Getter
@Setter
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status_of_number_available_to_connect")
    private boolean availableToConnectStatus;

    public Number(String phoneNumber, boolean availableToConnectStatus) {
        this.phoneNumber = phoneNumber;
        this.availableToConnectStatus = availableToConnectStatus;
    }

}
