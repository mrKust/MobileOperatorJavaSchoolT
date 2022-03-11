package com.school.database.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tariff_name")
    private String tariff_name;

    @Column(name = "price")
    private int price;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "tariff_options",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "options_id")
    )
    private List<Options> options;

    @OneToOne(mappedBy = "contractTariff")
    private Contract contract;

    @Column(name = "available_to_connect_status")
    private boolean availableToConnectOrNotStatus;

    public Tariff() {
    }

    public Tariff(String tariff_name, int price, boolean availableToConnectOrNotStatus) {
        this.tariff_name = tariff_name;
        this.price = price;
        this.availableToConnectOrNotStatus = availableToConnectOrNotStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariff_name() {
        return tariff_name;
    }

    public void setTariff_name(String tariff_name) {
        this.tariff_name = tariff_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isAvailableToConnectOrNotStatus() {
        return availableToConnectOrNotStatus;
    }

    public void setAvailableToConnectOrNotStatus(boolean availableToConnectOrNotStatus) {
        this.availableToConnectOrNotStatus = availableToConnectOrNotStatus;
    }
}
