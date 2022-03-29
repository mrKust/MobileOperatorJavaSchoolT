package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariff")
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
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

    public Tariff(String tariffName, int price, boolean availableToConnectOrNotStatus) {
        this.tariffName = tariffName;
        this.price = price;
        this.availableToConnectOrNotStatus = availableToConnectOrNotStatus;
    }

}
