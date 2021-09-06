package com.lxt.learnsource.jdbc;


import java.io.IOException;
import java.sql.*;

public class JdbcService {

    public static int insert(StudentEntity studentEntity) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "insert into student (id,name) values(" + studentEntity.getId() + ",'" + studentEntity.getName() + "')";
        try {
            i = conn.createStatement().executeUpdate(sql);
        } finally {
            DBUtils.closeResources(conn, null, null);
        }
        return i;
    }

    public static int insertPtmt(StudentEntity studentEntity) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "insert into student (id,name) values(?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentEntity.getId());
            pstmt.setString(2, studentEntity.getName());
            i = pstmt.executeUpdate();
        } finally {
            DBUtils.closeResources(conn, pstmt, null);
        }
        return i;
    }

    public static int update(StudentEntity studentEntity) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "update student set name='" + studentEntity.getName() + "' where id='" + studentEntity.getId() + "'";
        try {
            i = conn.createStatement().executeUpdate(sql);
            System.out.println("resutl: " + i);
        } finally {
            DBUtils.closeResources(conn, null, null);
        }
        return i;
    }

    public static int updatePtmt(StudentEntity studentEntity) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "update student set name='" + studentEntity.getName() + "' where id='" + studentEntity.getId() + "'";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        } finally {
            DBUtils.closeResources(conn, pstmt, null);
        }
        return i;
    }

    public static Integer getAll() throws SQLException {
        Connection conn = DBUtils.getConnection();
        String sql = "select * from student";
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } finally {
            DBUtils.closeResources(conn, null, rs);
        }
        return null;
    }

    public static void getAllPtmt() throws SQLException {
        Connection conn = DBUtils.getConnection();
        String sql = "select * from student";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } finally {
            DBUtils.closeResources(conn, pstmt, rs);
        }
    }

    public static int delete(String name) {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "delete from student where name='" + name + "'";
        try {
            i = conn.createStatement().executeUpdate(sql);
            System.out.println("resutl: " + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(conn, null, null);
        }
        return i;
    }

    public static int deletePtmt(String name) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "delete from student where name='" + name + "'";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(conn, pstmt, null);
        }
        return i;
    }

    public static void batchUpdate(StudentEntity studentEntity) {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        int k = 0;
        String sql = "insert into student (id,name) values(?,?)";
        String sqlUpdate = "update student set name='123' where id='" + studentEntity.getId() + "'";
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentEntity.getId());
            pstmt.setString(2, studentEntity.getName());
            i = pstmt.executeUpdate();
            k = pstmt.executeUpdate(sqlUpdate);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(conn, pstmt, null);
        }
    }

    public static void main(String[] args) throws Exception {
        StudentEntity s1 = new StudentEntity();
        s1.setId(1);
        s1.setName("insert");
        insert(s1);
        s1.setId(2);
        s1.setName("insertPtmt");
        insertPtmt(s1);
        s1.setId(2);
        s1.setName("update");
        update(s1);
        s1.setId(2);
        s1.setName("updatePtmt");
        updatePtmt(s1);
        getAll();
        getAllPtmt();
        delete("insert");
        deletePtmt("updatePtmt");
        StudentEntity s2 = new StudentEntity();
        s2.setId(11);
        s2.setName("batchTrans");
        batchUpdate(s2);

        testHikari();

    }
    private static void testHikari() throws IOException, SQLException {
        ResultSet rs = null;
        try {

            HikariUtils.getInstance().start();

            // statement用来执行SQL语句
            Statement statement = HikariUtils.getInstance().getConnection().createStatement();

            // 要执行的SQL语句id和content是表review中的项。
            String sql = "select * from student";

            // 得到结果
            rs = statement.executeQuery(sql);

            if (rs.next()) {
                System.out.println("have data");
            } else {
                System.out.println("not have data");
            }
        } finally {
            if (rs!= null) {
                rs.close();
            }
        }

    }

}
