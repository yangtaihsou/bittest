package com.bittest.platform.bg.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 数据库连接工具类
 * 2018-08-23.
 */
public class DbHelper {

    private static final Logger log = LoggerFactory.getLogger(DbHelper.class);

    // 此方法为获取数据库连接

    public static Connection getConnection(String connecturl, String username, String password) throws Exception {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
        String url = "jdbc:MySQL://" + connecturl;// 数据库
        Class.forName(driver); // 加载数据库驱动
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


    /**
     * 查【Query】
     *
     * @param sql
     * @return ResultSet
     */
    public static String executeQuery(String sql, Connection conn) throws Exception {
        String result = "";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                result = rs.getString(1);
            }
        } finally {
            free(rs, stmt, conn);
        }
        return result;
    }

    /**
     * 增删改
     *
     * @return
     */
    public static int executeUpdate(String sql, Connection conn) throws Exception {
        int result = -1;
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            result = pst.executeUpdate();
        } finally {
            free(null, pst, conn);
        }
        return result;
    }

    /**
     * 释放【ResultSet】资源
     *
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * 释放【Statement】资源
     *
     * @param st
     */
    public static void closeStatment(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    /**
     * 释放【Connection】资源
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }


    /**
     * 释放所有数据资源
     *
     * @param rs
     * @param st
     * @param conn
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        closeResultSet(rs);
        closeStatment(st);
        closeConnection(conn);
    }

    /**
     * sql检查点 查询内容
     *
     * @param connecturl
     * @param username
     * @param password
     * @param sql
     * @return
     * @throws Exception
     */
    public static String getResult(String connecturl, String username, String password, String sql) throws Exception {
        Connection connection = getConnection(connecturl, username, password);
        return executeQuery(sql, connection);
    }

    /**
     * 增删改
     *
     * @param connecturl
     * @param username
     * @param password
     * @param sql
     * @return
     * @throws Exception
     */
    public static int updateStmt(String connecturl, String username, String password, String sql) throws Exception {
        Connection conn = getConnection(connecturl, username, password);
        return executeUpdate(sql, conn);
    }
}
