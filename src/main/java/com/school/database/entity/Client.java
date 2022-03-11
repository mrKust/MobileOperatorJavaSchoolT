package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="client")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Getter
    @Setter
    private int id;

    @Column(name="first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name="surname")
    @Getter
    @Setter
    private String surname;

    @Column(name="date_of_birth")
    @Getter
    @Setter
    private String dateOfBirth;

    @Column(name="passport_number")
    @Getter
    @Setter
    private String passportNumber;

    @Column(name="address")
    @Getter
    @Setter
    private String address;

    @Column(name="phone_number")
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name="email_address")
    @Getter
    @Setter
    private String emailAddress;

    @Column(name="password_log_in")
    @Getter
    @Setter
    private String passwordLogIn;

    @OneToOne(mappedBy = "contractClient", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Contract contract;

    @Column(name = "status_of_number_block")
    @Getter
    @Setter
    private boolean clientNumberReadyToWorkStatus;

    @Column(name = "user_role")
    @Getter
    @Setter
    private String userRole;

    @Column(name = "user_role_blocked_number")
    @Getter
    @Setter
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
