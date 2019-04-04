package com.tracker.auth.ws.configs.databases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tokensEntityManagerFactory",
        transactionManagerRef = "tokensTransactionManager",
        basePackages = {"com.tracker.auth.ws.datasources.tokens.repo"}
)
public class TokensDBConfig {

    @Bean(name = "tokensDataSource")
    @ConfigurationProperties(prefix = "spring.tokens-datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tokensEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tokensEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tokensDataSource") DataSource dataSource
    ) {
        return builder.dataSource(dataSource)
                .packages("com.tracker.auth.ws.datasource.tokens.entities")
                .persistenceUnit("tokens")
                .build();
    }

    @Bean(name = "tokensTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("tokensEntityManagerFactory") EntityManagerFactory tokensEntityManagerFactory
    ) {
        return new JpaTransactionManager(tokensEntityManagerFactory);
    }

}
