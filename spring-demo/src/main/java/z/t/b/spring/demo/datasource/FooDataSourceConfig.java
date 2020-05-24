package z.t.b.spring.demo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Administrator
 * @date 2020-05-23 10:58
 */
@Configuration
@Slf4j
public class FooDataSourceConfig {

    @Bean
    @Resource
    public JdbcTemplate fooJdbcTemplate(DataSource barDataSource){
        return new JdbcTemplate(barDataSource);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource(){
        DataSourceProperties properties = fooDataSourceProperties();
        log.info("fooDataSource:{}", properties.getUrl());
        DataSource dataSource = properties.initializeDataSourceBuilder().build();
        log.info("fooDataSource:{}", dataSource);
        return dataSource;
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource){
        return new DataSourceTransactionManager(fooDataSource);
    }
}
