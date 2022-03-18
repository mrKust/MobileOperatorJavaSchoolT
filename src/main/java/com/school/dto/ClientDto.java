package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Number;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Client client;

    private String operationType;

    private String[] stringsNumbers;

    private String passwordString;

    private String passwordString2;

    public String wrapAvailableNumbersInJsonString(List<Number> allNumbers) {
        JSONArray jsonArray = new JSONArray();
        for (Number tmp : allNumbers) {
            if (tmp.isAvailableToConnectStatus())
                jsonArray.add(tmp.getPhoneNumber());
        }

        return jsonArray.toJSONString();
    }

    public String[] wrapAvailableNumbersInString(List<Number> allNumbers) {
        List<String> availableNumbers = new ArrayList<>();

        for (Number tmp: allNumbers) {
            if (tmp.isAvailableToConnectStatus())
                availableNumbers.add(tmp.getPhoneNumber());
        }

        return availableNumbers.toArray(new String[0]);
    }

    public String[] wrapUsedNumbersInString(List<Number> allNumbers) {
        List<String> usedNumbers = new ArrayList<>();

        for (Number tmp: allNumbers) {
            if (!tmp.isAvailableToConnectStatus())
                usedNumbers.add(tmp.getPhoneNumber());
        }

        return usedNumbers.toArray(new String[0]);
    }

    public String wrapUsedNumbersInJsonString(List<Number> allNumbers) {
        JSONArray jsonArray = new JSONArray();
        for (Number tmp : allNumbers) {
            if (!tmp.isAvailableToConnectStatus())
                jsonArray.add(tmp.getPhoneNumber());
        }

        return jsonArray.toJSONString();
    }

    public boolean checkIsUserEmailUniqueOrNot(List<Client> allClients) {

        for (Client tmp : allClients) {
            if (tmp.getEmailAddress().equals(this.client.getEmailAddress()))
                return false;
        }
        return true;
    }
}
