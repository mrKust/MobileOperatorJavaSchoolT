package com.school.dto;

import com.school.database.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Client client;

    private String operationType;

    private String passwordString;

    private String passwordString2;
}
