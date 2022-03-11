package com.school.dto;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

public class TariffDto {

    private Tariff tariff;

    private String[] stringsOptions;

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public String[] getStringsOptions() {
        return stringsOptions;
    }

    public void setStringsOptions(String[] stringsOptions) {
        this.stringsOptions = stringsOptions;
    }

    public List<Options> wrapStringsToList(List<Options> allOptions) {
        List<Options> result = new ArrayList<>();

        for (int i = 0; i < this.stringsOptions.length; i++) {
            int optionId = Integer.parseInt(this.stringsOptions[i]);
            for(Options option : allOptions) {
                if (option.getId() == optionId) {
                    result.add(option);
                    break;
                }
            }
        }

        return result;
    }
}
