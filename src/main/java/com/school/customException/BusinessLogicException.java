package com.school.customException;

import lombok.Getter;
import lombok.Setter;

public class BusinessLogicException extends RuntimeException{

    @Getter
    @Setter
    String redirectUrl;

    public BusinessLogicException(String errorMessage, String redirectUrl) {
        super(errorMessage);
        this.redirectUrl = redirectUrl;
    }
}
