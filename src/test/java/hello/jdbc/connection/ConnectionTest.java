package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.IntStream;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={} class={}", con1, con1.getClass());
        log.info("connection={} class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션을 획득
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={} class={}", con1, con1.getClass());
        log.info("connection={} class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
//        dataSource.setConnectionTimeout(3000);
        dataSource.setPoolName("MyPool");
        Thread.sleep(1000);

        Connection connection = dataSource.getConnection();
//        Connection con1 = dataSource.getConnection();
//        Connection con2 = dataSource.getConnection();
//        Connection con3 = dataSource.getConnection();
//        Connection con4 = dataSource.getConnection();
//        Connection con5 = dataSource.getConnection();
//
//        con2.close();
//        con1.close();
//        con3.close();
//        con4.close();

        Thread.sleep(10000);

    }

    private static void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        Connection con3 = dataSource.getConnection();
        Connection con4 = dataSource.getConnection();
        Connection con5 = dataSource.getConnection();
        Connection con6 = dataSource.getConnection();
        Connection con7 = dataSource.getConnection();
        Connection con8 = dataSource.getConnection();
        Connection con9 = dataSource.getConnection();
        Connection con10 = dataSource.getConnection();
        log.info("connection={}", con1);
        log.info("connection={}", con2);
        log.info("connection={}", con3);
        log.info("connection={}", con4);
        log.info("connection={}", con5);
        log.info("connection={}", con6);
        log.info("connection={}", con7);
        log.info("connection={}", con8);
        log.info("connection={}", con9);
        log.info("connection={}", con10);
    }
}
