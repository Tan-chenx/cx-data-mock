package com.cx.data.mock.parse;

import org.junit.Before;
import org.junit.Test;

public class SqlParseTest {

    SqlParse sqlParse;

    @Before
    public void setUp() {
        sqlParse = new SqlParse();
    }

    @Test
    public void testParseSql() {

        String sql = "create table user\n" +
                "(\n" +
                "    id          int auto_increment\n" +
                "        primary key,\n" +
                "    name        varchar(50)                        null,\n" +
                "    age         tinyint                            null,\n" +
                "    address     varchar(200)                       null,\n" +
                "    create_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    create_user int                                null,\n" +
                "    update_time datetime default CURRENT_TIMESTAMP not null,\n" +
                "    update_user int                                null\n" +
                ");";
        sqlParse.parseSql(sql);


    }
}