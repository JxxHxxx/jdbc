package hello.jdbc.service;

import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
@TestConfiguration
public class TestConfig {
    @Bean
    DataSource dataSource() {
        log.info("create dataSource bean");
        return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    @Bean // 프록시가 트랜잭션을 시작할 때 호출해서 쓴다.
    PlatformTransactionManager transactionManager() {
        log.info("create PlatformTransactionManager bean");
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    MemberRepositoryV3 memberRepositoryV3() {
        log.info("create MemberRepositoryV3 bean");
        return new MemberRepositoryV3(dataSource());
    }

    @Bean
    MemberServiceV3_3 memberServiceV3_3() {
        log.info("create MemberServiceV3_3 bean");
        return new MemberServiceV3_3(memberRepositoryV3());
    }
}
