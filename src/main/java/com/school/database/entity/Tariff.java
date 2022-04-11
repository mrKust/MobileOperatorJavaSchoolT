package com.school.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.school.customSerializer.CustomTariffSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents tariff's table from database
 */
@Entity
@Table(name = "tariff")
@NoArgsConstructor
@JsonSerialize(using = CustomTariffSerializer.class)
@Getter
@Setter
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tariff_name")
    private String tariffName;

    @Column(name = "price")
    private double price;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Fetch(value = FetchMode.SUBSELECT)
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

}
