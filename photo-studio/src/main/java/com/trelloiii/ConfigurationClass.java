package com.trelloiii;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.trelloiii.services","com.trelloiii"})
@EnableJpaRepositories("com.trelloiii.dao")
public class ConfigurationClass {
}
