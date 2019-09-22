/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

import java.time.Duration;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;

/**
 * @author hantsy
 */

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionFactory connectionFactory = new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .database("reactive")
                        .username("postgres")
                        .password("Ocbc2019").build()
        );

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder(connectionFactory)
                .validationQuery("SELECT 1")
                .maxIdleTime(Duration.ofMinutes(30))
                .initialSize(5)
                .maxSize(20)
                .build();

        return connectionFactory;
    }

}
//@Configuration
//public class DatabaseConfig {
//
//    @Bean
//    public PostgresqlConnectionFactory postgresqlConnectionFactory() {
//        return new PostgresqlConnectionFactory(
//            PostgresqlConnectionConfiguration.builder()
//                .host("localhost")
//                .database("test")
//                .username("user")
//                .password("password").build()
//        );
//    }
//
//    @Bean
//    DatabaseClient databaseClient(PostgresqlConnectionFactory connectionFactory ) {
//        return DatabaseClient.create(connectionFactory);
//    }
//
//    @Bean
//    PostRepository repository(R2dbcRepositoryFactory factory) {
//        return factory.getRepository(PostRepository.class);
//    }
//
//    @Bean
//    R2dbcRepositoryFactory factory(DatabaseClient client) {
//        RelationalMappingContext context = new RelationalMappingContext();
//        context.afterPropertiesSet();
//        return new R2dbcRepositoryFactory(client, context);
//    }
//
//}
//
