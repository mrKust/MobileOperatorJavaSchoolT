package com.school.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.school.customSerializer.CustomOptionSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents option's table from database
 */
@Entity
@Table(name = "options")
@NoArgsConstructor
@JsonSerialize(using = CustomOptionSerializer.class)
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
    private double price;

    @Column(name = "cost_to_add")
    private double costToAdd;

    @ManyToMany
    @JoinTable(
            name = "tariff_options",
            joinColumns = @JoinColumn(name = "options_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id")
    )
    private List<Tariff> availableForTariffs;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "contract_options",
            joinColumns = @JoinColumn(name = "options_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    private List<Contract> connectedToContracts;

    @Column(name = "available_for_connect_status")
    private boolean availableOptionToConnectOrNot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "options_type_id")
    private OptionType optionType;
}
