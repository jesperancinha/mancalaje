package com.jofisaes.mancala.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MancalaProdConfiguration {

    private static Logger logger = LoggerFactory.getLogger(MancalaProdConfiguration.class);


    public MancalaProdConfiguration(DataSource dataSource, @Value("classpath:org/springframework/session/jdbc/schema-postgresql.sql")
            Resource script) {
        try {
            ScriptUtils.executeSqlScript(dataSource.getConnection(), script);
        } catch (Exception e) {
            logger.trace("Initialization script may have not run correctly. Please check.");
        }
    }

}

