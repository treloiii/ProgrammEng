package com.trelloiii;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Конфигурациионный класс Spring, включает в IoC контейнер компоненты в выбранных пакетах и JPA репозитории
 * @author trelloiii
 */
@Configuration
@ComponentScan({"com.trelloiii.services","com.trelloiii"})
@EnableJpaRepositories("com.trelloiii.dao")
public class ConfigurationClass {
}
