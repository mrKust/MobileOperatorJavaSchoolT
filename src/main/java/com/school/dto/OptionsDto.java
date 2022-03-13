package com.school.dto;

import com.school.database.entity.Options;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class OptionsDto {

    @Getter
    @Setter
    private Options options;

    @Getter
    @Setter
    private String[] stringOptionCategory;

}