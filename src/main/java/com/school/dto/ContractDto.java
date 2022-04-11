package com.school.dto;

import com.school.database.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contract data transfer object for pass data from view to backend
 */
@NoArgsConstructor
@Getter
@Setter
public class ContractDto {

    private Contract contract;

    private String[] stringsTariff;

    private String[] stringsClients;

    private String[] stringsOptions;

    private String[] connectedOptions;

    private int id;

    private String blockedRole;

    private String[] stringsNumbers;

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

    public String[] castConnectedOptionsInStrings(List<Options> connectedOptions) {
        String[] result = new String[connectedOptions.size()];

        for (int i = 0; i < connectedOptions.size(); i++) {
            result[i] = Integer.toString(connectedOptions.get(i).getId());
        }

        return result;
    }

}
