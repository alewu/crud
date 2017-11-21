package com.koko.crud.util.freemarker.util;


import com.fasterxml.jackson.core.util.VersionUtil;
import com.koko.crud.util.freemarker.bean.Field;
import com.koko.crud.util.freemarker.bean.TableMetaData;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class TableUtils {
    public static final String TABLE = "table";
    public static final String FILED = "filed";

    private static final String REGEX = "_";
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String DRIVER;

    static {
        DRIVER = getParameter("jdbc.driverClass");
        URL = getParameter("jdbc.url");
        USER = getParameter("jdbc.username");
        PASSWORD = getParameter("jdbc.password");
    }


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getResultSet() {
        try (Connection connection = TableUtils.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            return databaseMetaData.getColumns(null, "%", "%", "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<TableMetaData> getTableMetaData() throws SQLException {
        TableMetaData tableMetaData = null;
        Field field = null;
        Connection connection = TableUtils.getConnection();
        DatabaseMetaData dbMetData = connection.getMetaData();
        ResultSet rs = dbMetData.getTables(null, "%", "%", new String[]{"TABLE"});
        List<TableMetaData> tableMetaDatas = new LinkedList<>();
        while (rs.next()) {
            tableMetaData = new TableMetaData();
            // 获取表名
            String tableName = rs.getString("TABLE_NAME");
            tableMetaData.setTableName(tableName);
            tableMetaData.setEntityName(TableUtils.processName(tableName, TableUtils.TABLE));
            // 根据表名提取表里面信息
            ResultSet colRS = dbMetData.getColumns(null, "%", tableName, "%");
            List<Field> fields = new LinkedList<>();
            while (colRS.next()) {
                field = new Field();
                String columnName = colRS.getString("COLUMN_NAME");
                String typeName = colRS.getString("TYPE_NAME");
                String remarks = colRS.getString("REMARKS");
                field.setColumnName(columnName);
                field.setMemberVariable(TableUtils.processName(columnName, FILED));
                field.setTypeName(typeName);
                field.setRemarks(remarks);
                fields.add(field);
            }
            tableMetaData.setFields(fields);
            tableMetaDatas.add(tableMetaData);
        }
        return tableMetaDatas;
    }

    public static String processName(String str, String target) {
        String[] words = str.split(REGEX);
        StringBuilder sb = new StringBuilder(words.length);
        String firstWord = StringUtils.uncapitalize(words[0].trim());
        sb.append(firstWord);
        for (int i = 1; i < words.length; i++) {
            String otherWord = StringUtils.capitalize(words[i].trim());
            sb.append(otherWord);
        }
        if (TABLE.equals(target)) {
            return sb.toString().replace(firstWord, "");
        }
        if (FILED.equals(target)) {
            return sb.toString();
        }
        return null;
    }

    public static String getParameter(String key) {
        Properties prop = new Properties();
        InputStream in = VersionUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key).trim();
    }
}
