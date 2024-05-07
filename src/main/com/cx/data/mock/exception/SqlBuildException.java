package com.cx.data.mock.exception;

/**
 * @author chenx
 * @date 2024/4/29-1:15
 */
public class SqlBuildException extends RuntimeException {

    public SqlBuildException() {
        super("构建insert语句失败！");
    }

    public SqlBuildException(String message) {
        super(message);
    }
}
