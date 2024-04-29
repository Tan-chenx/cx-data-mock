package com.cx.data.mock.constan;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenx
 * @version 1.0
 * @description: 数据库类型和Java类型映射关系
 * @date 2024/4/29 21:12
 */
public class DataTypeMapping {

   public static final Map<String,String> MAPPING_MAP = new HashMap<>(16);
    public DataTypeMapping() {
        MAPPING_MAP.put("char","String");
        MAPPING_MAP.put("blob","String");
        MAPPING_MAP.put("varchar","String");
        MAPPING_MAP.put("int","Integer");
        MAPPING_MAP.put("bigint","Long");
        MAPPING_MAP.put("datetime","Date");
        MAPPING_MAP.put("text","String");
        MAPPING_MAP.put("json","String");
        MAPPING_MAP.put("tinyint","Integer");
        MAPPING_MAP.put("decimal","BigDecimal");
        MAPPING_MAP.put("float","Float");
        MAPPING_MAP.put("double","Double");
        MAPPING_MAP.put("date","Date");
        MAPPING_MAP.put("time","Date");
        MAPPING_MAP.put("timestamp","Date");
        MAPPING_MAP.put("bit","Boolean");
        MAPPING_MAP.put("binary","byte[]");
        MAPPING_MAP.put("varbinary","byte[]");
        MAPPING_MAP.put("tinytext","String");
        MAPPING_MAP.put("mediumtext","String");
        MAPPING_MAP.put("longtext","String");
        MAPPING_MAP.put("enum","String");
        MAPPING_MAP.put("set","String");
        MAPPING_MAP.put("mediumblob","byte[]");
        MAPPING_MAP.put("longblob","byte[]");
    }

}
