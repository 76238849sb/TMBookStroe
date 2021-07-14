package com.zdj.TMBookStore.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` ':.          ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author 华韵流风
 * @ClassName DruidPool
 * @Description TODO
 * @Date 2021/5/7 14:46
 * @packageName util
 */
public class DruidPool {

    private static final ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 连接池，饿汉式，单例模式
     */
    private static final DruidDataSource pool = new DruidDataSource();

    //静态代码快，保证连接池对象在类加载过程中就完成初始化
    static {
        //对象连接池进行初始化，设置一些必须的参数，这些参数从外部文件中获取
        //创建属性集合对象
        Properties pt = new Properties();

        //把外部参数加载到该对象中
        InputStream inputStream = DruidPool.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            //利用字节输入流对象完成参数的加载
            pt.load(inputStream);
            //从集合中取出各参数，并设置连接池
            pool.setDriverClassName(pt.getProperty("driverName"));
            pool.setUrl(pt.getProperty("url"));
            pool.setUsername(pt.getProperty("user"));
            pool.setPassword(pt.getProperty("password"));
            //以上四个为必须要有的
            pool.setInitialSize(Integer.parseInt(pt.getProperty("InitialSize")));
            pool.setMinIdle(Integer.parseInt(pt.getProperty("MinIdle")));
            pool.setMaxActive(Integer.parseInt(pt.getProperty("MaxActive")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法返回一个连接对象给线程，并保证同一线程中只有一个连接对象
     *
     * @return Connection
     * @throws SQLException SQL
     */

    public static Connection getConnection() throws SQLException {
        if (tl.get() == null) {
            //当前线程有可能拿到一个不存在于tl中的连接对象
            return pool.getConnection();
        }
        return tl.get();
    }

    public static void beginTransaction() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            throw new SQLException("当前已有事务！不能开始");
        }
        connection = pool.getConnection();
        //设置为手动提交
        connection.setAutoCommit(false);
        tl.set(connection);
    }

    public static void commitTransaction() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            throw new SQLException("当前没有事务！不能提交");
        }
        //提交事务
        connection.commit();
        //释放连接
        connection.close();
        tl.remove();
    }

    public static void rollbackTransaction() throws SQLException {
        Connection connection = tl.get();
        if (connection == null) {
            throw new SQLException("当前没有事务");
        }
        //回滚事务
        connection.rollback();
        //释放连接
        connection.close();
        tl.remove();
    }


    public static void releaseConnection(Connection connection) throws SQLException {
        Connection threadCon = tl.get();
        if (threadCon == null) {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
