package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="surname")
    private String surname;

    @Column(name="date_of_birth")
    private String dateOfBirth;

    @Column(name="passport_number")
    private String passportNumber;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="password_log_in")
    private String passwordLogIn;

    @OneToOne(mappedBy = "contractClient", cascade = CascadeType.REMOVE)
    private Contract contract;

    @Column(name = "status_of_number_block")
    private boolean clientNumberReadyToWorkStatus;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_role_blocked_number")
    private String roleOfUserWhoBlockedNumber;

    public Client(String firstName, String surname, String dateOfBirth, String passportNumber, String address, String phoneNumber, String emailAddress, String passwordLogIn, boolean clientNumberReadyToWorkStatus, String userRole, String roleOfUserWhoBlockedNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.passportNumber = passportNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.passwordLogIn = passwordLogIn;
        this.clientNumberReadyToWorkStatus = clientNumberReadyToWorkStatus;
        this.userRole = userRole;
        this.roleOfUserWhoBlockedNumber = roleOfUserWhoBlockedNumber;
    }

}
