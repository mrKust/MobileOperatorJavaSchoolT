package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

public class ContractDto {

    private Contract contract;

    private String[] stringsTariff;

    private String[] stringsClients;

    private String[] stringsOptions;

    private String[] connectedOptions;

    private String operationType;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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

    public String[] getConnectedOptions() {
        return connectedOptions;
    }

    public void setConnectedOptions(String[] connectedOptions) {
        this.connectedOptions = connectedOptions;
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

    public List<Options> wrapStringsToConnectedOptions(List<Options> allOptions) {
        List<Options> connectedOptions = new ArrayList<>();

        for (Options tmp : allOptions) {
            for (int i = 0; i < this.stringsOptions.length; i++) {
                if (Integer.parseInt(this.stringsOptions[i]) == tmp.getId()) {
                    connectedOptions.add(tmp);
                    break;
                }

            }
        }

        return connectedOptions;
    }

    public String[] castConnectedOptionsInStrings(List<Options> connectedOptions) {
        String[] result = new String[connectedOptions.size()];

        for (int i = 0; i < connectedOptions.size(); i++) {
            result[i] = Integer.toString(connectedOptions.get(i).getId());
        }

        return result;
    }

}
