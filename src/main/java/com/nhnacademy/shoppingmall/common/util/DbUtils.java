package com.nhnacademy.shoppingmall.common.util;


import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

public class DbUtils {
    public DbUtils(){
        throw new IllegalStateException("Utility class");
    }

    private static final DataSource DATASOURCE;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();

        try {
            basicDataSource.setDriver(new com.mysql.cj.jdbc.Driver());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //todo#1-1 {ip},{database},{username},{password} 설정
        //todo#1-2 initialSize, maxTotal, maxIdle, minIdle 은 모두 5로 설정합니다.
        //todo#1-3 Validation Query를 설정하세요
        //todo#1-4 적절히 변경하세요
        basicDataSource.setUrl("jdbc:mysql://133.186.241.167:3306/nhn_academy_12");
        basicDataSource.setUsername("nhn_academy_12");
        basicDataSource.setPassword("jcR59.XEQFn[ES6o");

        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(2);
        basicDataSource.setMaxIdle(2);
        basicDataSource.setMinIdle(2);

        basicDataSource.setMaxWait(Duration.ofSeconds(2));
        basicDataSource.setValidationQuery("SELECT 1");
        basicDataSource.setTestOnBorrow(true);

        DATASOURCE = basicDataSource;

    }

    public static DataSource getDataSource(){
        return DATASOURCE;
    }

}

