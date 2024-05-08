package com.cx.data.mock.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author chenx
 * @version 1.0
 * @description: TODO
 * @date 2024/5/8 22:04
 */
public class SourceConnectionUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/mock";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin123";


//    public static void main(String[] args) {
//        Connection connection = connectToDatabase();
//        String createTableStatement = getCreateTableStatement(connection, "user");
//        System.out.println(createTableStatement);
//    }

    public static Connection connectToDatabase() {
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 创建数据库连接
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }
    public static String getCreateTableStatement(Connection connection, String tableName) {
        String createTableStatement = "";
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
            while (resultSet.next()) {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
                if (rs.next()) {
                    createTableStatement = rs.getString(2); // 通常建表语句位于第二列
                }
                rs.close();
                stmt.close();
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("获取建表语句失败");
            e.printStackTrace();
        }
        return createTableStatement;
    }
}


