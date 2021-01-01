package com.qf.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Richard
 * 2020/12/23 19:07
 */
public class DBUtils {

    private static DataSource dataSource;
    private static Properties properties;

    /**
     * 加载配置文件
     */
    static {
        properties = new Properties();
        try {
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     *
     * @param args 需要关闭的参数
     */
    public static void close(AutoCloseable... args) {
        if (args == null || args.length == 0) {
            return;
        }

        for (AutoCloseable arg : args) {
            try {
                arg.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取properties
     *
     * @return properties
     */
    public static Properties getProperties() {
        return properties;
    }

}
