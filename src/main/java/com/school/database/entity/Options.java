package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "options")
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_type_id")
    private OptionType optionType;

    public Options(String optionsName, int price, int costToAdd, boolean availableOptionToConnectOrNot) {
        this.optionsName = optionsName;
        this.price = price;
        this.costToAdd = costToAdd;
        this.availableOptionToConnectOrNot = availableOptionToConnectOrNot;
        this.availableForTariffs = new ArrayList<>();
    }

}
