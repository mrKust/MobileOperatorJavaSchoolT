package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariff")
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "tariff_name")
    @Getter
    @Setter
    private String tariffName;

    @Column(name = "price")
    @Getter
    @Setter
    private int price;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "tariff_options",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "options_id")
    )
    @Getter
    @Setter
    private List<Options> options;

    @OneToOne(mappedBy = "contractTariff")
    @Getter
    @Setter
    private Contract contract;

    @Column(name = "available_to_connect_status")
    @Getter
    @Setter
    private boolean availableToConnectOrNotStatus;

    public Tariff(String tariffName, int price, boolean availableToConnectOrNotStatus) {
        this.tariffName = tariffName;
        this.price = price;
        this.availableToConnectOrNotStatus = availableToConnectOrNotStatus;
    }

}
