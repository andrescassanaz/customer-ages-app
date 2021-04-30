package com.andrescassanaz.customeragesapp.adapter.database.exception;

import com.andrescassanaz.customeragesapp.config.ErrorCode;
import com.andrescassanaz.customeragesapp.config.GenericException;

public class DuplicateElementException extends GenericException {
    public DuplicateElementException(ErrorCode errorCode, Throwable e) {
        super(errorCode, "", e);
    }
}
