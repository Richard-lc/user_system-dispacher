package com.qf.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard
 * 2020/12/23 19:23
 */
public class DaoUtils {

    /**
     * 公共的更新方法
     *
     * @param sql  预编译的sql语句
     * @param args 参数
     * @return 影响的行数
     */
    public static int commonUpdate(String sql, Object... args) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(sql);
            if (args != null && args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    prst.setObject(i + 1, args[i]);
                }
            }
            return prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(prst, connection);
        }
        return 0;
    }

    /**
     * 公共的查询方法
     *
     * @param sql  预编译sql语句
     * @param cls  实体类对象
     * @param args 参数
     * @param <T>  实体类类型
     * @return list集合
     */
    public static <T> List<T> commonQuery(String sql, Class<T> cls, Object... args) {
        Connection connection = DBUtils.getConnection();
        List<T> list = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            if (args != null && args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    prst.setObject(i + 1, args[i]);
                }
            }
            rs = prst.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                T t = cls.newInstance();
                Field[] fields = cls.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    Object value;
                    try {
                        value = rs.getObject(name);
                    } catch (SQLException e) {
                        value = rs.getObject(DBUtils.getProperties().getProperty(name));
                    }
                    field.setAccessible(true);
                    field.set(t, value);
                }
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, prst, connection);
        }
        return list;
    }


    public static Integer commonGetCount(String sql) {

        Connection connection = DBUtils.getConnection();
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement(sql);
            rs = prst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, prst, connection);
        }
        return 0;
    }
}
