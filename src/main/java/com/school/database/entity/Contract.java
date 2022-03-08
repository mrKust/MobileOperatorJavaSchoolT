package com.school.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff contractTariff;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client contractClient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_options",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "options_id")
    )
    private List<Options> ConnectedOptions;

    public Contract() {
    }

    public Contract(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addConnectedOptions(Options newOption) {
        if (ConnectedOptions == null) {
            ConnectedOptions = new ArrayList<Options>();
        }
        /*if (!contractTariff.getOptions().contains(newOption))
            throw new IllegalArgumentException("Can't connect option which not available for this tariff");
        if (contractTariff.getOptions().contains(newOption))
            throw new IllegalArgumentException("Option have been already connected to this contract");
*/
        ConnectedOptions.add(newOption);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Tariff getContractTariff() {
        return contractTariff;
    }

    public void setContractTariff(Tariff contractTariff) {
        this.contractTariff = contractTariff;
    }

    public Client getContractClient() {
        return contractClient;
    }

    public void setContractClient(Client contractClient) {
        this.contractClient = contractClient;
    }

    public List<Options> getConnectedOptions() {
        return ConnectedOptions;
    }

    public void setConnectedOptions(List<Options> options) {
        this.ConnectedOptions = options;
    }
}
