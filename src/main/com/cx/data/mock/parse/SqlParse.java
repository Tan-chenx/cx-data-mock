package com.cx.data.mock.parse;

import com.cx.data.mock.constan.SqlKeyWords;
import com.cx.data.mock.exception.SqlBuildException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.cx.data.mock.constan.CharConstant.BLANK;
import static com.cx.data.mock.constan.CharConstant.LEFT_BRACKET;
import static com.cx.data.mock.constan.CharConstant.SINGLE_QUOTE;
import static com.cx.data.mock.constan.SourceConstant.JSON_OBJECT;


/**
 * @author chenx
 * @date 2024/4/29-1:14
 */
@Slf4j
public class SqlParse {

    public String SqlParse(String sql) {
        String result = null;
        Statement statement;
        try {
            statement = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            log.error("sql解析异常", e);
            throw new SqlBuildException("sql解析异常:"+e.getMessage());
        }
        // 获取操作类型，例如 Create
        if (statement instanceof CreateTable) {
            result = buildInsertSql((CreateTable) statement);
        }
        return result;
    }

    /**
     * 构建insert语句
     *
     * @param statement
     */
    private String buildInsertSql(CreateTable statement) {
        CreateTable createTable = statement;
        Table table = createTable.getTable();
        String tableName = table.getName();
        // 构建insert语句
        StringBuilder insertSql = new StringBuilder(SqlKeyWords.INSERT_PRX);
        insertSql.append(tableName).append(LEFT_BRACKET);
        List<ColumnDefinition> columnDefinitions = createTable.getColumnDefinitions();
        // 列定义
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (ColumnDefinition column : columnDefinitions) {
            if (columns.length() > 0) {
                columns.append(", ");
                values.append(", ");
            }
            // 列名
            String columnName = column.getColumnName();
            columns.append(columnName);
            // 根据数据类型生成随机值
            String dataType = column.getColDataType().getDataType();
            List<String> argumentsStringList = column.getColDataType().getArgumentsStringList();
            List<Integer> arrayData = column.getColDataType().getArrayData();
            try {
                //TODO 根据字段自增属性值进行随机值生成
                values.append(generateRandomValue(dataType, argumentsStringList));
            } catch (SqlBuildException e) {
                throw new SqlBuildException("列：" + columnName + " 生成值失败:" + e.getMessage());
            }
        }
        insertSql.append(columns).append(") VALUES (").append(values).append(");");
        log.info("{}", insertSql);
        return insertSql.toString();
    }

    private static String generateRandomValue(String dataType, List<String> argumentsStringList) throws SqlBuildException {
        log.info("数据类型:{},参数：{}", dataType, argumentsStringList);
        int length;
        switch (dataType.toLowerCase()) {
            case "char":
            case "blob":
            case "varchar":
            case "text":
            case "tinytext":
            case "mediumtext":
            case "longtext":
            case "enum":
            case "set":
                length = argumentsStringList != null && !argumentsStringList.isEmpty() ? Integer.parseInt(argumentsStringList.get(0)) : 10;
                return SINGLE_QUOTE + RandomStringUtils.randomAlphabetic(length) + SINGLE_QUOTE;
            case "json":
                return SINGLE_QUOTE + JSON_OBJECT+ SINGLE_QUOTE;
            case "tinyint":
                int tinyintLength = argumentsStringList != null && !argumentsStringList.isEmpty() ? Integer.parseInt(argumentsStringList.get(0)) : 2;
                return RandomStringUtils.randomNumeric(tinyintLength);
            case "bigint":
            case "int":
            case "integer":
                length = argumentsStringList != null && !argumentsStringList.isEmpty() ? Integer.parseInt(argumentsStringList.get(0)) : 10;
                return RandomStringUtils.randomNumeric(length);
            case "decimal":
                /*
                    decimal(m,d):
                    m是数字的最大位数，他的范围是从1-65
                    d是小数点后的位数，他的范围是0-30，并且不能大于m
                    如果m被省略了，那么m的值默认为10，
                    如果d被省略了，那么d的值默认为0.
                    最大数长度为m-d,d为小数
                 */
                if (argumentsStringList == null || argumentsStringList.isEmpty()) {
                    length = 10;
                    return RandomStringUtils.randomNumeric(length);
                } else if (argumentsStringList.size() == 1) {
                    return RandomStringUtils.randomNumeric(Integer.parseInt(argumentsStringList.get(0)));
                } else if (argumentsStringList.size() == 2) {
                    int m = Integer.parseInt(argumentsStringList.get(0));
                    int d = Integer.parseInt(argumentsStringList.get(1));
                    if (m > d){
                        return RandomStringUtils.randomNumeric(m - d)
                                + "." + RandomStringUtils.randomNumeric(d);
                    }else {
                        String errorMsg = "decimal参数错误,最大位数" + m + "不能小于小数点后的位数" + d;
                        log.error(errorMsg);
                        throw new SqlBuildException(errorMsg);
                    }
                } else {
                    String errorMsg = "decimal参数错误";
                    log.error(errorMsg);
                    throw new SqlBuildException(errorMsg);
                }

            case "datetime":
                return SINGLE_QUOTE + LocalDate.now() + BLANK + LocalTime.now() + SINGLE_QUOTE;

            case "date":
                return SINGLE_QUOTE + LocalDate.now() + SINGLE_QUOTE;

            case "time":
                return SINGLE_QUOTE + LocalTime.now() + SINGLE_QUOTE;

            default:
                return SINGLE_QUOTE + "unknown" + SINGLE_QUOTE;

        }

    }

}
