package com.andrescassanaz.customeragesapp.adapter.controller.exception;

import com.andrescassanaz.customeragesapp.config.ErrorCode;
import com.andrescassanaz.customeragesapp.config.GenericException;

public class ControllerValidationException extends GenericException {
    public ControllerValidationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
