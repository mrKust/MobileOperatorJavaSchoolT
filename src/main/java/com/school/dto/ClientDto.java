package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Tariff;

import java.util.List;

public class ClientDto {

    private Client object;

    private String[] strings;

    public Client getObject() {
        return object;
    }

    public void setObject(Client object) {
        this.object = object;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public Tariff findTariffObjectById(List<Tariff> tariffList) {
        int tariffId = Integer.parseInt(strings[0]);
        for (Tariff tmp : tariffList) {
            if (tmp.getId() == tariffId)
                return tmp;
        }
        return null;
    }
}
