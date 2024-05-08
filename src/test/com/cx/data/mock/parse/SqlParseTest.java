package com.cx.data.mock.parse;

import com.cx.data.mock.exception.SqlBuildException;
import com.cx.data.mock.utils.SourceConnectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertTrue;

public class SqlParseTest {


    SqlParse sqlParse;

    @Before
    public void setUp() {
        sqlParse = new SqlParse();
    }

    /**
     * @description 连接真实数据库，获取建表语句
     * @expect 生成insert语句
     * @author chenx
     */

    @Test
    public void testParseSql2() {
        Connection connection = SourceConnectionUtils.connectToDatabase();
        assert connection != null;
        String sql = SourceConnectionUtils.getCreateTableStatement(connection, "user");
        String insertSql = sqlParse.SqlParse(sql);
        assertTrue(insertSql.startsWith("insert into `user`"));
    }

    /**
     * @description 手动编写的sql
     * @expect 生成insert语句
     * @author chenx
     */

    @Test
    public void testParseSql() {
        String sql = "create table user\n" +
                "(\n" +
                "    id          bigint auto_increment\n" +
                "        primary key,\n" +
                "    name        varchar(50)                        null,\n" +
                "    age         tinyint                            null,\n" +
                "    address     varchar(200)                       null,\n" +
                "    create_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    create_user bigint                             null,\n" +
                "    update_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    update_user bigint                             null,\n" +
                "    weight      decimal(3, 2)                      null comment '体重',\n" +
                "    height      decimal                            null comment '体重',\n" +
                "    json_str    json                               null\n" +
                ");";
        sqlParse.SqlParse(sql);


    }

    /**
     * @description create table 解析异常
     * @expect sql解析异常
     * @author chenx
     */

    @Test(expected = SqlBuildException.class)
    public void testParseSql_ex() {
        String sql = "create table ss user\n" +
                "(\n" +
                "    id          bigint auto_increment\n" +
                "        primary key,\n" +
                "    name        varchar(50)                        null,\n" +
                "    age         tinyint                            null,\n" +
                "    address     varchar(200)                       null,\n" +
                "    create_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    create_user bigint                             null,\n" +
                "    update_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    update_user bigint                             null,\n" +
                "    weight      decimal(3, 2)                      null comment '体重',\n" +
                "    height      decimal                            null comment '体重',\n" +
                "    json_str    json                               null\n" +
                ");";
        String insertSql = sqlParse.SqlParse(sql);
        assertTrue(insertSql.startsWith("insert into `user`"));
    }

    /**
     * @description decimal参数错误
     * @expect SqlBuildException
     * @author chenx
     */

    @Test(expected = SqlBuildException.class)
    public void testParseSql_ex_decimal(){
        String sql = "create table  user\n" +
                "(\n" +
                "    id          bigint auto_increment\n" +
                "        primary key,\n" +
                "    name        varchar(50)                        null,\n" +
                "    age         tinyint                            null,\n" +
                "    address     varchar(200)                       null,\n" +
                "    create_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    create_user bigint                             null,\n" +
                "    update_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    update_user bigint                             null,\n" +
                "    weight      decimal(3, 2,1)                      null comment '体重',\n" +
                "    height      decimal                            null comment '体重',\n" +
                "    json_str    json                               null\n" +
                ");";
        sqlParse.SqlParse(sql);
    }


    @Test
    public void testTime() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        System.out.println("currentDate = " + currentDate);
        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("currentDateTime = " + currentDateTime);
        System.out.println("currentTime = " + currentTime);
        StringBuilder sb = new StringBuilder("asdfasdf1234546789asdfasdf,");
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("sb = " + sb);
    }
}