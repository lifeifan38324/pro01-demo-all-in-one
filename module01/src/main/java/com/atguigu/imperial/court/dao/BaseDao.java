package com.atguigu.imperial.court.dao;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lff
 * @Dsecription
 * @create 2022/05/11 17:05
 */
public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();

    /**
     *
     * @param sql
     * @param entityClass
     * @param parameters
     * @return
     */
    public T getSingleBean(String sql,Class<T> entityClass, Object ... parameters){
        // 获取数据库连接
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection,sql,new BeanHandler<>(entityClass),parameters);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param sql
     * @param entityClass
     * @param parameters
     * @return
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            // 获取数据库连接
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);

        } catch (SQLException e) {
            e.printStackTrace();

            // 如果真的抛出异常，则将编译时异常封装为运行时异常抛出
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param sql
     * @param parameters
     * @return
     */
    public int update(String sql, Object ... parameters) {

        try {
            Connection connection = JDBCUtils.getConnection();

            int affectedRowNumbers = runner.update(connection, sql, parameters);

            return affectedRowNumbers;
        } catch (SQLException e) {
            e.printStackTrace();

            // 如果真的抛出异常，则将编译时异常封装为运行时异常抛出
            throw new RuntimeException(e);
        }
    }
}
