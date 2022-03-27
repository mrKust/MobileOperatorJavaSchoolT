package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Number;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Client client;

    private String operationType;

    private String[] stringsNumbers;

    private String passwordString;

    private String passwordString2;

    private String blockedRole;

    private int id;
}
