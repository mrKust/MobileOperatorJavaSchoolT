package com.school.dto;

import com.school.database.entity.Options;
import com.school.database.entity.Tariff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TariffDto {

    private Tariff tariff;

    private String[] stringsOptions;

    private String operationType;

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
