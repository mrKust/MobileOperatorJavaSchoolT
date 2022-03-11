package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "numbers_base")
@NoArgsConstructor
public class Number {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "phone_number")
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name = "status_of_number_available_to_connect")
    @Getter
    @Setter
    private boolean availableToConnectStatus;

    public Number(String phoneNumber, boolean availableToConnectStatus) {
        this.phoneNumber = phoneNumber;
        this.availableToConnectStatus = availableToConnectStatus;
    }

}
