package com.school.dto;

import com.school.database.entity.Number;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NumberDto {

    private String phoneNumber;

    public boolean checkNumberToUnique(List<Number> allNumbers) {

        for (Number tmp : allNumbers) {

            if (phoneNumber.equals(tmp.getPhoneNumber()))
                return false;

        }

        return true;
    }
}
