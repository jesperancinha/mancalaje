package com.jofisaes.mancala.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@Profile("prod")
public class MancalaProdConfiguration {

    private static Logger logger = LoggerFactory.getLogger(MancalaProdConfiguration.class);
    @Value("classpath:org/springframework/session/jdbc/schema-postgresql.sql")
    Resource script;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("admin");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/mancalajedb");
        DataSource dataSource = dataSourceBuilder.build();
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), script);
        } catch (Exception e){
            logger.trace("Initialization script may have not run correctly. Please check.");
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

