package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "option_type")
@NoArgsConstructor
public class OptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "options_type_name")
    @Getter
    @Setter
    private String optionType;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "optionType",
    orphanRemoval = true)
    @Getter
    @Setter
    private List<Options> options;

    public OptionType(String optionType) {
        this.optionType = optionType;
    }
}
