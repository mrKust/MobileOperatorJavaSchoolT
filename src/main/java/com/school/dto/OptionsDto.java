package com.school.dto;

import com.school.database.entity.Options;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OptionsDto {

    private Options options;

    private String[] stringOptionCategory;

    private int pageSize;

    private String sortColumn;
}