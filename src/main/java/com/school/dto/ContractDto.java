package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Contract;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ContractDto {

    @Getter
    @Setter
    private Contract contract;

    @Getter
    @Setter
    private String[] stringsTariff;

    @Getter
    @Setter
    private String[] stringsClients;

    @Getter
    @Setter
    private String[] stringsOptions;

    @Getter
    @Setter
    private String[] connectedOptions;

    @Getter
    @Setter
    private String operationType;

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
