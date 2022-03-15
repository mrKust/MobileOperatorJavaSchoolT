package com.school.dto;

import com.school.database.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (this.stringsOptions == null) {
            return connectedOptions;
        }

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

    public boolean checkChosenOptionForCorrect (List<Options> chosenOptions) {
        Map<Integer, Integer> optionTypeCount = new HashMap<>();
        for (Options tmp : chosenOptions) {
            Integer count = optionTypeCount.get(tmp.getOptionType().getId());
            optionTypeCount.put(tmp.getOptionType().getId(), count == null ? 1 : count + 1);

        }

        for (Map.Entry<Integer, Integer> entry : optionTypeCount.entrySet()) {
            if (entry.getValue() > 1 )
                return false;
        }


        return true;
    }
}
