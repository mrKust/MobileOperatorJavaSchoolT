package com.school.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "options_type")
@NoArgsConstructor
@Getter
@Setter
public class OptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "options_type_name")
    private String optionType;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "optionType",
    orphanRemoval = true)
    private List<Options> options;

}
