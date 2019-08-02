package com.jofisaes.mancala.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@Profile("prod")
@Slf4j
public class MancalaProdConfiguration {


    public MancalaProdConfiguration(DataSource dataSource, @Value("classpath:org/springframework/session/jdbc/schema-postgresql.sql")
            Resource script) {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), script);
        } catch (Exception e) {
            log.trace("Initialization script may have not run correctly. Please check.");
        }
    }

}

