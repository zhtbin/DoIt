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
public class BarDataSourceConfig {

    @Bean
    @Resource
    public JdbcTemplate barJdbcTemplate(DataSource barDataSource){
        return new JdbcTemplate(barDataSource);
    }

    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDataSource(){
        DataSourceProperties properties = barDataSourceProperties();
        log.info("barDataSource:{}", properties.getUrl());
        DataSource dataSource = properties.initializeDataSourceBuilder().build();
        log.info("barDataSource:{}", dataSource);
        return dataSource;
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTxManager(DataSource barDataSource){
        return new DataSourceTransactionManager(barDataSource);
    }
}
