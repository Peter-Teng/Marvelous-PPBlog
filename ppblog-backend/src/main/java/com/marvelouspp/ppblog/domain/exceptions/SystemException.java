package com.marvelouspp.ppblog.domain.exceptions;

import com.marvelouspp.ppblog.domain.enums.Code;

public class SystemException extends RuntimeException{

    private Code code;

    public Code getCode() {
        return this.code;
    }

    public SystemException(Code code) {
        super(code.getMessage());
        this.code = code;
    }
}
