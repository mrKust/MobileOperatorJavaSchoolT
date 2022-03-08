package com.school.dto;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

public class TariffDto {

    private Tariff object;
    private String[] strings;

    public Tariff getObject() {
        return object;
    }

    public void setObject(Tariff object) {
        this.object = object;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public List<Options> wrapStringsToList(List<Options> allOptions) {
        List<Options> result = new ArrayList<>();

        for (int i = 0; i < this.strings.length; i++) {
            int optionId = Integer.parseInt(this.strings[i]);
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
