package com.school.dto;

import com.school.database.entity.Options;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Options data transfer object for pass data from view to backend
 */
@NoArgsConstructor
@Getter
@Setter
public class OptionsDto {

    private Options options;

    private String[] stringOptionCategory;

    private int pageSize;

    private String sortColumn;
}