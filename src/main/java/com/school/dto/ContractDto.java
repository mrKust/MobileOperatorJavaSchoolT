package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Tariff;

import java.util.List;

public class ContractDto {

    private Contract object;

    private String[] stringsTariff;

    private String[] stringsClients;

    private String[] stringsOptions;

    public Contract getObject() {
        return object;
    }

    public void setObject(Contract object) {
        this.object = object;
    }

    public String[] getStringsTariff() {
        return stringsTariff;
    }

    public void setStringsTariff(String[] stringsTariff) {
        this.stringsTariff = stringsTariff;
    }

    public String[] getStringsClients() {
        return stringsClients;
    }

    public void setStringsClients(String[] stringsClients) {
        this.stringsClients = stringsClients;
    }

    public String[] getStringsOptions() {
        return stringsOptions;
    }

    public void setStringsOptions(String[] stringsOptions) {
        this.stringsOptions = stringsOptions;
    }

    public Tariff wrapStringsToTariff(List<Tariff> allTariffs) {

        int choosenTariffId = Integer.parseInt(stringsTariff[0]);
        for (Tariff tmp : allTariffs) {
            if (choosenTariffId == tmp.getId())
                return tmp;
        }
        return null;
    }

    public Client wrapStringsToClient(List<Client> allClients) {
        int choosenClientId = Integer.parseInt(stringsClients[0]);
        for (Client tmp : allClients) {
            if (choosenClientId == tmp.getId())
                return tmp;
        }
        return null;
    }
}
