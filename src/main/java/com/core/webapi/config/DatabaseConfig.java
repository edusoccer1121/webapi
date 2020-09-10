package com.core.webapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.core.webapi.repository"
        },
        entityManagerFactoryRef = "myEntityManager",
        transactionManagerRef = "myTransactionManager"
)
@ComponentScan(
        basePackages = {
                "com.core.webapi.model",
                "com.core.webapi.repository.jdbc"
        }
)
public class DatabaseConfig {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    private final DatabaseProperties databaseProperties;
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties, JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.databaseProperties = databaseProperties;
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean myEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(myDataSource());
        em.setPackagesToScan(
                "com.core.webapi.model"
        );
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.put(Environment.HBM2DDL_AUTO, hibernateProperties.getDdlAuto());
        properties.put(Environment.DIALECT, jpaProperties.getDatabasePlatform());
        properties.put(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false");
        properties.setProperty("org.hibernate.envers.audit_table_prefix", "audit_");
        properties.setProperty("org.hibernate.envers.audit_table_suffix", "");
        properties.setProperty("org.hibernate.envers.revision_field_name", "revision");
        properties.setProperty("org.hibernate.envers.revision_type_field_name", "revision_type");

        em.setJpaProperties(properties);

        return em;
    }
    @Bean(destroyMethod="close")
    @Primary
    public DataSource myDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(databaseProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(databaseProperties.getUrl());
        hikariConfig.setUsername(databaseProperties.getUsername());
        hikariConfig.setPassword(databaseProperties.getPassword());

        //hikariConfig.setMaximumPoolSize(databaseProperties.getHikari().getMaximumPoolSize());
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP-1");
        //hikariConfig.setRegisterMbeans(true);

        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", Boolean.TRUE);
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", Boolean.TRUE);

        return new HikariDataSource(hikariConfig);
    }
    @Bean
    @Primary
    public PlatformTransactionManager myTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(myEntityManager().getObject());

        return transactionManager;
    }
}
