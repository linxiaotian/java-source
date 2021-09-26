package com.lxt.learnsource.jdbc;

import java.sql.*;

public final class DBUtils {

    private static String url = "jdbc:mysql://localhost:3307/sharding_db";
    private static String user = "root";
    private static String psw = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private DBUtils() {

    }

    /**
     * 获取数据库的连接
     * @return conn
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, psw);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 释放资源
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if(null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if(null != pstmt) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    } finally {
                        if(null != conn) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }


}