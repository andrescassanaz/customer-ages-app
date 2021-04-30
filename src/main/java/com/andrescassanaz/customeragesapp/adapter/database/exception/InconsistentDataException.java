package com.andrescassanaz.customeragesapp.adapter.database.exception;

import com.andrescassanaz.customeragesapp.config.ErrorCode;
import com.andrescassanaz.customeragesapp.config.GenericException;

public class InconsistentDataException extends GenericException {

    public InconsistentDataException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InconsistentDataException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InconsistentDataException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
