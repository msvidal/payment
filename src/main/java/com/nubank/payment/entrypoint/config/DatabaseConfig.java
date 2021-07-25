package com.nubank.payment.entrypoint.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories({
    "com.nubank.payment.entrypoint.database"
})
@EntityScan({
    "com.nubank.payment.entrypoint.database.entity"
})
public class DatabaseConfig {

}
