package com.school.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "options_name")
    private String optionsName;

    @Column(name = "price")
    private int price;

    @Column(name = "cost_to_add")
    private int costToAdd;

    @ManyToMany
    @JoinTable(
            name = "tariff_options",
            joinColumns = @JoinColumn(name = "options_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id")
    )
    private List<Tariff> availableForTariffs;

    @ManyToMany
    @JoinTable(
            name = "contract_options",
            joinColumns = @JoinColumn(name = "options_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    private List<Contract> connectedToContracts;

    @Column(name = "available_for_connect_status")
    private boolean availableOptionToConnectOrNot;

    public Options() {
    }

    public Options(String optionsName, int price, int costToAdd, boolean availableOptionToConnectOrNot) {
        this.optionsName = optionsName;
        this.price = price;
        this.costToAdd = costToAdd;
        this.availableOptionToConnectOrNot = availableOptionToConnectOrNot;
    }

    public void addTariffInAvailableList(Tariff tariff) {
        if (availableForTariffs == null) {
            availableForTariffs = new ArrayList<>();
        }
        /*if (this.getAvailableForTariffs().contains(tariff))
            throw new IllegalArgumentException("Option already available for this tariff");*/
        availableForTariffs.add(tariff);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionsName() {
        return optionsName;
    }

    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0)
            throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }

    public int getCostToAdd() {
        return costToAdd;
    }

    public void setCostToAdd(int costToAdd) {
        if (costToAdd < 0)
            throw new IllegalArgumentException("Cost to add must be positive");
        this.costToAdd = costToAdd;
    }

    public List<Tariff> getAvailableForTariffs() {
        return availableForTariffs;
    }

    public void setAvailableForTariffs(List<Tariff> availableForTariffs) {
        this.availableForTariffs = availableForTariffs;
    }

    public boolean isAvailableOptionToConnectOrNot() {
        return availableOptionToConnectOrNot;
    }

    public void setAvailableOptionToConnectOrNot(boolean availableOptionToConnectOrNot) {
        this.availableOptionToConnectOrNot = availableOptionToConnectOrNot;
    }
}
