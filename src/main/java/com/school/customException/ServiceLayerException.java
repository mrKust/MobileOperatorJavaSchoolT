package com.school.customException;

import lombok.Getter;
import lombok.Setter;

/**
 * Class describe custom exception
 * Those exceptions throwing only from service layer
 */
@Getter
@Setter
public class ServiceLayerException extends RuntimeException{

    public ServiceLayerException(String errorMessageForUser) {
        super(errorMessageForUser);
    }

}
