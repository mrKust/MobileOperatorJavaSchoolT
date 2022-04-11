package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents client's table from database
 */
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

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="password_log_in")
    private String passwordLogIn;

    @OneToMany(mappedBy = "contractClient", cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Contract> contractClient;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "money_balance")
    private double moneyBalance;

}
