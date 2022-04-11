package com.school.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Number data transfer object for pass data from view to backend
 */
@NoArgsConstructor
@Getter
@Setter
public class NumberDto {

    private String phoneNumber;

}
