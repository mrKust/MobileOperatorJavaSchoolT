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
@Getter
@Setter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status_of_number_block")
    private boolean contractBlockStatus;

    @Column(name = "user_role_blocked_number")
    private String roleOfUserWhoBlockedContract;

    @Column(name = "contract_cost")
    private double priceForContractPerMonth;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff contractTariff;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client contractClient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_options",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "options_id")
    )
    private List<Options> ConnectedOptions;

}
