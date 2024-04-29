package com.cx.data.mock.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author chenx
 * @version 1.0
 * @description: 建表语句实体
 * @date 2024/4/29 20:53
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CreateTableBo {
    /**
     * 表名
     */
    private String tableName;
    private List<ColumnBo> columns;


}
