package com.cx.data.mock.parse;

import com.cx.data.mock.bean.ColumnBo;
import com.cx.data.mock.bean.CreateTableBo;
import com.cx.data.mock.exception.SqlParseException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.cx.data.mock.constan.CharConstant.*;
import static com.cx.data.mock.constan.SqlKeyWords.CREATE_TABLE;

/**
 * @author chenx
 * @date 2024/4/29-1:14
 */
@Slf4j
public class SqlParse {

    /**
     * 根据sql语句解析出表名和字段名
     */
    public void parseSql(String sql) {
        String formatSql = formatSql(sql);
        if (!formatSql.startsWith(CREATE_TABLE)) {
            throw new SqlParseException("sql not a create table sql!");
        }
        CreateTableBo createTableBo = new CreateTableBo();
        // 获取表名
        String tableName = formatSql.substring(formatSql.indexOf(CREATE_TABLE) + CREATE_TABLE.length(), formatSql.indexOf("("));
        createTableBo.setTableName(tableName);
        List<ColumnBo> columns =new ArrayList<>();
        // 获取字段名
        String fields = formatSql.substring(formatSql.indexOf(LEFT_BRACKET) + 1, formatSql.lastIndexOf(RIGHT_BRACKET));
        // 解析字段名
        String[] fieldArray = fields.split(COMMA);
        for (String field : fieldArray) {
            // 获取字段名和字段类型
            String[] fieldInfo = field.trim().split("\\s+");
            ColumnBo column = new ColumnBo();
            //这两个类型是必须指定
            column.setColumnName(fieldInfo[0]);
            String filedType = fieldInfo[1];
            if (filedType.endsWith(RIGHT_BRACKET)) {
                //指定了数据长度
                column.setColumnType(filedType.substring(0, filedType.indexOf(RIGHT_BRACKET)));
                column.setColumnSize(Integer.valueOf(filedType.substring(filedType.indexOf(LEFT_BRACKET) + 1, filedType.indexOf(RIGHT_BRACKET))));
            } else {
                column.setColumnType(filedType);
            }
            columns.add(column);
        }
        createTableBo.setColumns(columns);

        log.info("{}",createTableBo);
    }


    /**
     * @description: 格式化Sql
     * @param: sql
     * @return: String
     * @author chenx
     * @date: 2024/4/29 21:29
     */
    public String formatSql(String sql) {
        //转小写
        sql = sql.toLowerCase();
        //去除头部和尾部的空格
        sql = sql.trim();
        //去除中间多余的空格
        return sql.replaceAll("\\s+", " ");
    }

}
