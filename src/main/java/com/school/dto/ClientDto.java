package com.school.dto;

import com.school.database.entity.Client;
import com.school.database.entity.Number;

import org.json.simple.JSONArray;

import java.util.List;

public class ClientDto {

    private Client client;

    private String operationType;

    private String stringsNumbers;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getStringsNumbers() {
        return stringsNumbers;
    }

    public void setStringsNumbers(String stringsNumbers) {
        this.stringsNumbers = stringsNumbers;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String wrapAvailableNumbersInJsonString(List<Number> allNumbers) {
        JSONArray jsonArray = new JSONArray();
        for (Number tmp : allNumbers) {
            if (tmp.isAvailableToConnectStatus())
                jsonArray.add(tmp.getPhoneNumber());
        }

        return jsonArray.toJSONString();
    }
}
