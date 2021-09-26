package com.lxt.learnsource.jdbc;


import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static int insertOrder(Connection conn, OrderInfoEntity order) {
        int i = 0;
        String sql = "insert into t_order (order_id,order_sn,user_id,receive_user,province,city,district,address,payment_method,order_money\n" +
                ",district_money,express_money,payment_money,express_name,express_sn,create_time,deliver_time,pay_time,receive_time,order_status,\n" +
                "order_point,modified_time)  values(" +"" + order.getOrderId() + "," + order.getOrderSn() + "," + order.getUserId() + ",'" + order.getReceiveUser() + "'," + order.getProvinve()
                + "," + order.getCity() + "," + order.getDistrict() + ",'" + order.getAddress() + "'," + order.getPaymentMethod()
                + "," + order.getOrderMoney() + "," + order.getDistrictMoney() + "," + order.getExpressMoney()
                + "," + order.getPaymentMoney() + ",'" + order.getExpressName() + "','" + order.getExpressSn()
                + "',now(),now(),now(),now()" + "," + order.getOrderStatus() + "," + order.getOrderPoint() + ",now());";
        try {
            i = conn.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(conn, null, null);
        }
        return i;
    }

    public static int insertOrderPtmt(Connection conn, OrderInfoEntity order) {
        int i = 0;
        String sql = "insert into t_order (order_id,order_sn,user_id,receive_user,province,city,district,address,payment_method,order_money\n" +
                ",district_money,express_money,payment_money,express_name,express_sn,create_time,deliver_time,pay_time,receive_time,order_status,\n" +
                "order_point,modified_time)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now(),now(),now(),?,?,now())";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderId());
            pstmt.setString(2, order.getOrderSn());
            pstmt.setString(3,order.getUserId());
            pstmt.setString(4, order.getReceiveUser());
            pstmt.setString(5, order.getProvinve());
            pstmt.setString(6, order.getCity());
            pstmt.setString(7, order.getDistrict());
            pstmt.setString(8, order.getAddress());
            pstmt.setString(9, order.getPaymentMethod());
            pstmt.setString(10, order.getOrderMoney());
            pstmt.setString(11, order.getDistrictMoney());
            pstmt.setString(12, order.getExpressMoney());
            pstmt.setString(13, order.getPaymentMoney());
            pstmt.setString(14, order.getExpressName());
            pstmt.setString(15, order.getExpressSn());
            pstmt.setString(16, order.getOrderStatus());
            pstmt.setString(17, order.getOrderPoint());
            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResources(conn, pstmt, null);
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

    public static int updateOrder(OrderInfoEntity order) throws SQLException {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "update t_order set address='" + order.getAddress() + "' where order_id=" + order.getOrderId() + " and user_id=" + order.getUserId();
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

    public static Integer getAllOrder() throws SQLException {
        Connection conn = DBUtils.getConnection();
        String sql = "select * from t_order";
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
            int col = rs.getMetaData().getColumnCount();
            System.out.println("allOrder============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("allOrder============================");
        } finally {
            DBUtils.closeResources(conn, null, rs);
        }
        return null;
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

    public static int deleteOrder(String userId, String orderId) {
        Connection conn = DBUtils.getConnection();
        int i = 0;
        String sql = "delete from t_order where user_id=" + userId + " and order_id=" + orderId;
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
//        StudentEntity s1 = new StudentEntity();
//        s1.setId(1);
//        s1.setName("insert");
//        insert(s1);
//        s1.setId(2);
//        s1.setName("insertPtmt");
//        insertPtmt(s1);
//        s1.setId(2);
//        s1.setName("update");
//        update(s1);
//        s1.setId(2);
//        s1.setName("updatePtmt");
//        updatePtmt(s1);
//        getAll();
//        getAllPtmt();
//        delete("insert");
//        deletePtmt("updatePtmt");
//        StudentEntity s2 = new StudentEntity();
//        s2.setId(11);
//        s2.setName("batchTrans");
//        batchUpdate(s2);
//
//        testHikari();
        OrderInfoEntity order = new OrderInfoEntity();
        order.setOrderId("2");
        order.setOrderSn("1000002");
        order.setUserId("3");
        order.setReceiveUser("huanzhang");
        order.setProvinve("010");
        order.setCity("010");
        order.setDistrict("124");
        order.setAddress("二道港2幢");
        order.setPaymentMethod("1");
        order.setOrderMoney("100.2");
        order.setDistrictMoney("10.2");
        order.setExpressMoney("5.0");
        order.setPaymentMoney("200.3");

        order.setExpressName("申通");
        order.setExpressSn("1000234234");
        order.setOrderStatus("1");
        order.setOrderPoint("100");
        //HikariUtils.getInstance().start();
        Connection conn = DBUtils.getConnection();
        //insertOrder(conn,order);
        OrderInfoEntity updateOrder = new OrderInfoEntity();
        updateOrder.setOrderId("2");
        updateOrder.setUserId("3");
        updateOrder.setAddress("sjlsdfjslf");
        updateOrder(updateOrder);
        getAllOrder();
        deleteOrder("3", "2");

//        ExecutorService service = Executors.newFixedThreadPool(100);
//        HikariUtils.getInstance().start();
//        for (int i = 1; i < 101; i++) {
//            Connection conn = HikariUtils.getInstance().getConnection();
//            service.execute(new InsertJob(i,conn));
//        }
//        service.shutdown();

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
