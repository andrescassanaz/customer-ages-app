package com.andrescassanaz.customeragesapp.adapter.database.exception;

import com.andrescassanaz.customeragesapp.config.ErrorCode;
import com.andrescassanaz.customeragesapp.config.GenericException;

public class DatabaseException extends GenericException {
    public DatabaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
