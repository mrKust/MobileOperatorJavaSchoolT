package com.school.dto;

import com.school.database.entity.Tariff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Tariff data transfer object for pass data from view to backend
 */
@NoArgsConstructor
@Getter
@Setter
public class TariffDto {

    private Tariff tariff;

    private String[] stringsOptions;

    private int pageSize;

    private String sortColumn;

    public List<Integer> getChosenOptionsList() {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < this.stringsOptions.length; i++) {
            int optionId = Integer.parseInt(this.stringsOptions[i]);
            result.add(optionId);
        }

        return result;
    }
}
