package cn.cc.dawn.config.data.druid;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootTest
class Sp03DataApplicationTests {

    @Autowired
    DataSource dataSource;


    @Test
    void contextLoads() {

        /**
         * 查看默认的数据源
         * class com.zaxxer.hikari.HikariDataSource
         *
         */
        System.out.println(dataSource.getClass());
        try {

            DruidDataSource druidDataSource = (DruidDataSource) dataSource;

            log.info("druidDataSource.getInitialSize(): " + druidDataSource.getInitialSize());

            Connection connection = dataSource.getConnection();
            log.info("connection: " + connection);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
