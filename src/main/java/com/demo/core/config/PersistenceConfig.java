package com.demo.core.config;


import com.demo.core.repository.BaseRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.demo.core.repository",
        repositoryBaseClass = BaseRepository.class)
@EnableJpaAuditing
public class PersistenceConfig {
}
