package com.school.database.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="surname")
    private String surname;

    @Column(name="date_of_birth")
    private String date_of_birth;

    @Column(name="passport_number")
    private String passport_number;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phone_number;

    @Column(name="email_address")
    private String email_address;

    @Column(name="password_log_in")
    private String password_log_in;

    @OneToOne(mappedBy = "contractClient")
    private Contract contract;

    @Column(name = "status_of_number_block")
    private boolean clientNumberBlockStatus;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_role_blocked_number")
    private String roleOfUserWhoBlockedNumber;

    public Client() {
    }

    public Client(String first_name, String surname, String date_of_birth, String passport_number, String address, String phone_number, String email_address, String password_log_in, boolean clientNumberBlockStatus, String userRole, String roleOfUserWhoBlockedNumber) {
        this.first_name = first_name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.passport_number = passport_number;
        this.address = address;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.password_log_in = password_log_in;
        this.clientNumberBlockStatus = clientNumberBlockStatus;
        this.userRole = userRole;
        this.roleOfUserWhoBlockedNumber = roleOfUserWhoBlockedNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword_log_in() {
        return password_log_in;
    }

    public void setPassword_log_in(String password_log_in) {
        this.password_log_in = password_log_in;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isClientNumberBlockStatus() {
        return clientNumberBlockStatus;
    }

    public void setClientNumberBlockStatus(boolean clientNumberBlockStatus) {
        this.clientNumberBlockStatus = clientNumberBlockStatus;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRoleOfUserWhoBlockedNumber() {
        return roleOfUserWhoBlockedNumber;
    }

    public void setRoleOfUserWhoBlockedNumber(String roleOfUserWhoBlockedNumber) {
        this.roleOfUserWhoBlockedNumber = roleOfUserWhoBlockedNumber;
    }
}
