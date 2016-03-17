package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 连接数据库的工具类
 * 
 * @Title: DBManager.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2013-8-7 下午02:45:23
 * @author 杨凯
 */
public class DBManager {

    private static DataSource ds;

    private static ThreadLocal<Connection> map = new ThreadLocal<Connection>();

    public static DataSource getDataSource(String source) {
        ds = new ComboPooledDataSource(source);
        return ds;
    }

    /*
     * 获取数据库链接的方法
     */
    public static Connection getConnection() {
        Connection con = map.get();
        try {
            if (con == null) {
                con = ds.getConnection();
                con.setAutoCommit(false);
                map.set(con);
            }
            return con;
        } catch (SQLException e) {
            throw new RuntimeException("获取conn对象的时候出现错误");
        }
        /*
         * 回滚事务的方法
         */
    }

    public static void rollBack() {
        Connection con = map.get();
        if (con != null) {
            try {
                con.rollback();
                con.commit();
            } catch (SQLException e) {
                throw new RuntimeException("回滚的时候出错了");
            }
        }
    }

    /*
     * 关闭链接对象的方法
     */
    public static void close() {
        Connection con = map.get();
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭的时候出错了");
            } finally {
                map.remove();
            }
        }
    }

    /*
     * 提交事务的方法
     */
    public static void commit() {
        Connection con = map.get();
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException e) {
                throw new RuntimeException("提交事务的时候出错了");
            }
        }
    }
}
