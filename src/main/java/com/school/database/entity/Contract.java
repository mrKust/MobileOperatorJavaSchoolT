package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract")
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "phone_number")
    @Getter
    @Setter
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    @Getter
    @Setter
    private Tariff contractTariff;

    @OneToOne
    @JoinColumn(name = "client_id")
    @Getter
    @Setter
    private Client contractClient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_options",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "options_id")
    )
    @Getter
    @Setter
    private List<Options> ConnectedOptions;

    public Contract(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.ConnectedOptions = new ArrayList<>();
    }

}
