package com.school.customException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessLogicException extends RuntimeException{

    String redirectUrl;
    String errorMessageForUser;

    public BusinessLogicException(String errorMessage, String redirectUrl, String errorMessageForUser) {
        super(errorMessage);
        this.redirectUrl = redirectUrl;
        this.errorMessageForUser = errorMessageForUser;
    }
}
