package com.cx.data.mock.parse;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SqlParseTest {

    SqlParse sqlParse;

    @Before
    public void setUp() {
        sqlParse = new SqlParse();
    }

    @Test
    public void testParseSql() {
        String sql = "-- mock.`user` definition\n" +
                "\n" +
                "CREATE TABLE `user` (\n" +
                "  `id` bigint NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(50) DEFAULT NULL,\n" +
                "  `age` tinyint DEFAULT NULL,\n" +
                "  `address` varchar(200) DEFAULT NULL,\n" +
                "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `create_user` bigint DEFAULT NULL,\n" +
                "  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "  `update_user` bigint DEFAULT NULL,\n" +
                "  `weight` decimal(3,2) DEFAULT NULL COMMENT '体重',\n" +
                "  `height` decimal(10,0) DEFAULT NULL COMMENT '体重',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `user_name_index` (`name`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=708268401 DEFAULT CHARSET=utf8;";
        sqlParse.SqlParse(sql);


    }

    @Test
    public void testTime(){
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
        sb.deleteCharAt(sb.length()-1);
        System.out.println("sb = " + sb);
    }
}