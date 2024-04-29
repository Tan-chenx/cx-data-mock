package com.cx.data.mock.exception;

/**
 * @author chenx
 * @date 2024/4/29-1:15
 */
public class SqlParseException extends RuntimeException {

    public SqlParseException() {
    }

    public SqlParseException(String message) {
        super(message);
    }
}
