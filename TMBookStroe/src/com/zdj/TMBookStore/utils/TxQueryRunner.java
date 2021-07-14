package com.zdj.TMBookStore.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

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
 * @ClassName TxQueryRunner
 * @Description TODO
 * @Date 2021/5/8 18:00
 * @packageName com.zhong.test
 */
public class TxQueryRunner extends QueryRunner {
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection connection = DruidPool.getConnection();
        int[] result = super.batch(connection, sql, params);
        DruidPool.releaseConnection(connection);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection connection = DruidPool.getConnection();
        T result = super.query(connection, sql, rsh, params);
        DruidPool.releaseConnection(connection);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection connection = DruidPool.getConnection();
        T result = super.query(connection, sql, rsh);
        DruidPool.releaseConnection(connection);
        return result;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection connection = DruidPool.getConnection();
        int result = super.update(connection, sql);
        DruidPool.releaseConnection(connection);
        return result;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection connection = DruidPool.getConnection();
        int result = super.update(connection, sql,param);
        DruidPool.releaseConnection(connection);
        return result;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection connection = DruidPool.getConnection();
        int result = super.update(connection, sql,params);
        DruidPool.releaseConnection(connection);
        return result;
    }
}
