package com.cx.data.mock.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chenx
 * @version 1.0
 * @description: TODO
 * @date 2024/4/29 21:42
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ColumnBo {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * 数据大小
     */
    private Integer columnSize;
}

