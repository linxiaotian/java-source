package com.lxt.learnsource.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariUtils {

    private static Logger logger = LoggerFactory.getLogger(HikariUtils.class);

    private static final String DB_CONFIG_FILE = "/db.properties";

    // 数据库连接数
    private short db_max_conn = 0;

    // 数据库服务器addr
    private String db_url = null;

    // 数据库连接端口
    private short db_port = 0;

    // 数据库名称
    private String db_name = null;

    // 数据库登录用户名
    private String db_username = null;

    // 数据库登录密码
    private String db_password = null;

    // 数据库连接
    private HikariDataSource dataSource;

    private static HikariUtils hikariUtils;

    public static HikariUtils getInstance(){
        if (hikariUtils == null) {
            hikariUtils = new HikariUtils();
        }
        return hikariUtils;
    }

    public void start() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream in = HikariUtils.class.getClass().getResourceAsStream(DB_CONFIG_FILE);
        properties.load(in);

        db_max_conn = Short.valueOf(properties.getProperty("db_max_conn"));
        db_url = String.valueOf(properties.getProperty("db_url"));
        db_port = Short.valueOf(properties.getProperty("db_port"));
        db_name = String.valueOf(properties.getProperty("db_name"));
        db_username = String.valueOf(properties.getProperty("db_username"));
        db_password = String.valueOf(properties.getProperty("db_password"));

        if (db_url == null || db_url.length() == 0) {
            logger.error("配置的数据库ip地址错误!");
            System.exit(0);
        }

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://"+ db_url +":" + db_port +"/" + db_name + "?useUnicode=true&characterEncoding=utf8&useSSL=false");
        config.setUsername(db_username);
        config.setPassword(db_password);
        config.setMaximumPoolSize(db_max_conn);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        // 设置连接超时为8小时
        config.setConnectionTimeout(8 * 60 * 60);
        dataSource = new HikariDataSource(config);
    }


    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            dataSource.resumePool();
            return null;
        }
    }

    public boolean stop() throws SQLException {
        dataSource.close();
        return true;
    }
}
