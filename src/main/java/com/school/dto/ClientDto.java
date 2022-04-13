package com.school.dto;

import com.school.database.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client data transfer object for pass data from view to backend
 */
@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Client client;

    private String passwordString;

    private String passwordString2;

    private int money;

    private int pageSize;

    private String sortColumn;
}
