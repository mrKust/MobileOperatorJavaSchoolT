package com.school.customException;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ServiceLayerException extends RuntimeException{

    public ServiceLayerException(String errorMessageForUser) {
        super(errorMessageForUser);
    }

}
